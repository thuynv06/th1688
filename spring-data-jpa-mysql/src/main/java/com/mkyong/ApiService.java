package com.mkyong;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.json.JSONObject;


@Service
public class ApiService {
    private final WebClient webClient;

    @Autowired
    private KienHangRepository kienHangRepository;

    @Autowired
    private MvdRepository mvdRepository;

    public ApiService() {
        this.webClient = WebClient.builder().baseUrl("http://nhaphang.alibao.vn/dang-nhap.html").build();
    }

//    public void login(String username, String password) {
//        LoginRequest loginRequest = new LoginRequest(username, password);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);
//
//        webClient.post().uri("")
//                .bodyValue(requestEntity)
//                .exchange()
//                .flatMap(response -> response.toEntity(String.class))
//                .subscribe(responseEntity -> {
//                    // Handle the login response
//                    String responseBody = Objects.requireNonNull(responseEntity.getBody());
//                    System.out.println(responseBody);
//
//
////                    // Retrieve cookies from the response headers
////                    List<HttpCookie> cookies = responseEntity.getCookies().getCookies();
////
////                     //Store or use the retrieved cookies as needed
////                    for (HttpCookie cookie : cookies) {
////                        System.out.println("Cookie: " + cookie.getName() + "=" + cookie.getValue());
////                    }
//                });
//    }

    public void callApi() {
        String url = "http://nhaphang.alibao.vn/dang-nhap.html";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
        } else {
            System.out.println("Request failed with status code: " + response.getStatusCode());
        }
    }
    public  String  getKg(String str){
        String text = str;

        // Extract the value before the "kg" delimiter
        int kgIndex = text.indexOf("kg");
        if (kgIndex != -1) {
            String value = text.substring(0, kgIndex).trim();
//            System.out.println("Extracted Value: " + value);
            return value;
        } else {
            System.out.println("Value not found.");
            return null;
        }
    }

    public void callApiWithCookies(String cookies,String numberPages) {
        String url = "https://nhaphang.alibao.vn/customer-packages?page=".concat(numberPages);

        WebClient webClient = WebClient.builder().baseUrl(url).build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE,cookies );
        // Add more cookies if necessary

        webClient.get()
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(responseBody -> {
                    // Parse HTML response
                    Document doc = Jsoup.parse(responseBody);
                    // Extract table data
                    Element table = doc.select("table").first();
                    if (table != null) {
                        Elements rows = table.select("tr");
//                        System.out.println("rows.size();: " + rows.size());
                        // Create a SimpleDateFormat object to parse the date and time
                        SimpleDateFormat inputDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        // Define the date format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        int numberNewMVD=0;
                        int numberUpdatesSuccess=0;
                        for (int i = rows.size()-1; i >=1 ; i --){
                            KienhangEntity kh= new KienhangEntity();
                            List<String> formattedDates = new ArrayList<>();
                            Elements cells = rows.get(i).select("td");
                            for(int j= 0; j < cells.size(); j ++){
                                Elements divs = cells.get(j).select("div");

                                for(int k= 0; k < divs.size(); k++){
                                    if (divs.get(k).hasClass("alibaoPagePackage_ListItem_PackageCode")) {
                                        String ladingCode = divs.get(k).text().trim();
                                        kh.setLadingcode(ladingCode);
                                        kh.setName(ladingCode);
//                                        System.out.println("Package Code: " + ladingCode);
                                    } else if (divs.get(k).hasClass("alibaoPagePackage_ListItem_OrderCode_Weight")) {
                                        String packageStatus = divs.get(k).text().trim();
                                        String kg= getKg(packageStatus);
                                        kh.setSize(NumberUtils.parseNumber(kg, Double.class));
//                                        System.out.println("Số cân: " + getKg(packageStatus));
                                    }else if (divs.get(k).hasClass("alibaoPagePackage_ListItem_OrderCode_Time_Right")) {
                                        String dateTimeString  = divs.get(k).text().trim();
                                        if (!dateTimeString.equals("--")){
                                            Date dateTime = null;
                                            try {
                                                dateTime = inputDateFormat.parse(dateTimeString);
                                            } catch (ParseException e) {
                                                throw new RuntimeException(e);
                                            }
                                            String formattedDateTime = outputDateFormat.format(dateTime);
                                            formattedDates.add(formattedDateTime);
                                        }
                                    }
                                }
                            }
                            JSONObject json = new JSONObject();

                            for (int u = 0; u < formattedDates.size(); u++) {
                                if ( u > 2){
                                    break;
                                }else if( u==2){
                                    // Parse the formatted date string
                                    LocalDateTime parsedDate = LocalDateTime.parse(formattedDates.get(u), formatter);
                                    // Add 3 hours to the parsed date
                                    LocalDateTime addedHours = parsedDate.plusHours(3);
                                    String DateNhapKhoVn = addedHours.format(formatter);
                                    json.put(String.valueOf(u + 2), DateNhapKhoVn);
                                }else {
                                    json.put(String.valueOf(u + 2), formattedDates.get(u));
                                }
                                kh.setStatus(u + 2);
                            }
                            kh.setListtimestatus(json.toString());
//                            System.out.println("ListTime: " + json);
                            // check xm có mvd chua
                            List<KienhangEntity> listK= kienHangRepository.findByMVD(kh.getLadingcode());
                            if (listK == null || listK.size() == 0) {
                                System.out.println("Chua có thì lưu về ");
                                kh.setAmount(1.00);
                                kh.setType(1);
                                kh.setFeetransport(25000.00);
                                kh.setTotalfeetransport(25000.00*kh.getSize());
                                kh.setShippingway("BT/HN1");
                                kienHangRepository.save(kh);
                                System.out.println("KH: " + kh.toString() );
                                numberNewMVD++;
                            }else{ // có rồi thì updated
//                                System.out.println("time" + listK.get(0).getListtimestatus());
                                if (kh.getStatus() > listK.get(0).getStatus() ){
                                    listK.get(0);
                                    listK.get(0).setListtimestatus(json.toString());
                                    listK.get(0).setStatus(kh.getStatus());
                                    kienHangRepository.save(listK.get(0));
                                    numberUpdatesSuccess++;
                                    System.out.println("Updated_MVD:" + kh.toString());
                                }
                            }
//                            System.out.println("-------------- " );
                        }
                        System.out.println("Pages:"+ numberPages + " | Lưu mới: " + numberNewMVD + "| cập nhập: " + numberUpdatesSuccess );
//                        for (Element row : rows) {
//                            Elements cells = row.select("td");
//                            for (Element cell : cells) {
//                                Elements divs = cell.select("div");
//                                for (Element div : divs) {
//                                    if (div.hasClass("alibaoPagePackage_ListItem_PackageCode")) {
//                                        String ladingCode = div.text().trim();
//                                        System.out.println("Package Code: " + ladingCode);
//                                    } else if (div.hasClass("alibaoPagePackage_ListItem_OrderCode_Weight")) {
//                                        String packageStatus = div.text().trim();
//                                        String kg= getKg(packageStatus);
//                                        System.out.println("Số cân: " + getKg(packageStatus));
//                                    }else if (div.hasClass("alibaoPagePackage_ListItem_OrderCode_Time_Right")) {
//                                        String packageStatus = div.text().trim();
//                                        System.out.println("ListTime: " + packageStatus);
//                                    }
//                                    // Add more conditions for other class names if needed
//                                    // alibaoPagePackage_ListItem_OrderCode_Weight
//                                }
//                            }
//                            System.out.println("-------------- " );
//                            KienhangEntity k = new KienhangEntity(ladingCode, 1,kg, 0, null, ladingCode);
                    } else {
                        System.out.println("No table found in the HTML response.");
                    }
                });
    }
    public void callApiToCloneData(String cookies,String numberPages) {
        String url = "https://nhaphang.alibao.vn/customer-packages?page=".concat(numberPages);

        WebClient webClient = WebClient.builder().baseUrl(url).build();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE,cookies );
        // Add more cookies if necessary

        webClient.get()
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(responseBody -> {
                    // Parse HTML response
                    Document doc = Jsoup.parse(responseBody);
                    // Extract table data
                    Element table = doc.select("table").first();
                    if (table != null) {
                        Elements rows = table.select("tr");
//                        System.out.println("rows.size();: " + rows.size());
                        // Create a SimpleDateFormat object to parse the date and time
                        SimpleDateFormat inputDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        // Define the date format
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        int numberNewMVD=0;
                        int numberUpdatesSuccess=0;
                        for (int i = rows.size()-1; i >=1 ; i --){
                            Mvd m= new Mvd();
                            List<String> formattedDates = new ArrayList<>();
                            Elements cells = rows.get(i).select("td");
                            for(int j= 0; j < cells.size(); j ++){
                                Elements divs = cells.get(j).select("div");

                                for(int k= 0; k < divs.size(); k++){
                                    if (divs.get(k).hasClass("alibaoPagePackage_ListItem_PackageCode")) {
                                        String mvd = divs.get(k).text().trim();
                                        m.setMvd(mvd);
                                        m.setName(mvd);
//                                        System.out.println("Package Code: " + ladingCode);
                                    } else if (divs.get(k).hasClass("alibaoPagePackage_ListItem_OrderCode_Weight")) {
                                        String packageStatus = divs.get(k).text().trim();
                                        String kg= getKg(packageStatus);
                                        m.setCannang(NumberUtils.parseNumber(kg, Double.class));
//                                        System.out.println("Số cân: " + getKg(packageStatus));
                                    }else if (divs.get(k).hasClass("alibaoPagePackage_ListItem_OrderCode_Time_Right")) {
                                        String dateTimeString  = divs.get(k).text().trim();
                                        if (!dateTimeString.equals("--")){
                                            Date dateTime = null;
                                            try {
                                                dateTime = inputDateFormat.parse(dateTimeString);
                                            } catch (ParseException e) {
                                                throw new RuntimeException(e);
                                            }
                                            String formattedDateTime = outputDateFormat.format(dateTime);
                                            formattedDates.add(formattedDateTime);
                                        }
                                    }
                                }
                            }
                            JSONObject json = new JSONObject();

                            for (int u = 0; u < formattedDates.size(); u++) {
                                if ( u > 2){
                                    break;
                                }else if( u==2){
                                    // Parse the formatted date string
                                    LocalDateTime parsedDate = LocalDateTime.parse(formattedDates.get(u), formatter);
                                    // Add 3 hours to the parsed date
                                    LocalDateTime addedHours = parsedDate.plusHours(3);
                                    String DateNhapKhoVn = addedHours.format(formatter);
                                    json.put(String.valueOf(u + 1), DateNhapKhoVn);
                                }else {
                                    json.put(String.valueOf(u + 1), formattedDates.get(u));
                                }
                                m.setStatus(u + 1);
                            }
                            m.setTimes(json.toString());
//                            System.out.println("ListTime: " + json);
                            // check xm có mvd chua
                            List<Mvd> listMVD= mvdRepository.findByMaVanDon(m.getMvd());
                            if (listMVD == null || listMVD.size() == 0) {
                                System.out.println("Chua có thì lưu về ");
                                m.setGiavc(25000.0);
                                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                                Double t=m.getGiavc()*m.getCannang();
                                String formattedResult = decimalFormat.format(t);
                                double parsedValue = Double.valueOf(formattedResult);
                                m.setThanhtien(parsedValue);
                                m.setLine("BT/HN1");
                                mvdRepository.save(m);
                                System.out.println("KH: " + m.toString() );
                                numberNewMVD++;
                            }else{ // có rồi thì updated
//                                System.out.println("time" + listK.get(0).getListtimestatus());
                                if (m.getStatus() > listMVD.get(0).getStatus() ){
                                    listMVD.get(0);
                                    listMVD.get(0).setTimes(json.toString());
                                    listMVD.get(0).setStatus(m.getStatus());
                                    mvdRepository.save(listMVD.get(0));
                                    numberUpdatesSuccess++;
                                    System.out.println("Updated_MVD:" + m.toString());
                                }
                            }
//                            System.out.println("-------------- " );
                        }
                        System.out.println("Pages:"+ numberPages + " | Lưu mới: " + numberNewMVD + "| cập nhập: " + numberUpdatesSuccess );
                    } else {
                        System.out.println("No table found in the HTML response.");
                    }
                });
    }
}