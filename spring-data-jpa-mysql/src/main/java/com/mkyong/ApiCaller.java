package com.mkyong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class ApiCaller {
    @Autowired
    private ApiService apiService;
    public  void calAPIgetCookies() {
        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Create a MultiValueMap to hold the form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("login", "hieunguyen");
        formData.add("password", "123456");

        // Create HttpHeaders with the required Content-Type
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create a RequestCallback to set the form data and headers
        RequestCallback requestCallback = request -> {
            request.getHeaders().addAll(headers);
            request.getBody().write(formData.toString().getBytes());
        };

        // Create a ResponseExtractor to extract the cookies from the response
        ResponseExtractor<ResponseEntity<String>> responseExtractor = restTemplate.responseEntityExtractor(String.class);

        // Make the API call with form data and retrieve the cookies
        ResponseEntity<String> responseEntity = restTemplate.execute(
                "https://nhaphang.alibao.vn/login",
                HttpMethod.POST,
                requestCallback,
                responseExtractor
        );

        // Get the cookies from the response headers
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println("responseHeaders: " + responseHeaders);
        // Print the value of "laravel_session"

//        String cookies = responseHeaders.getFirst(HttpHeaders.SET_COOKIE);
        List<String> cookies =responseHeaders.getValuesAsList("set-cookie");
        System.out.println("Cookies: " + cookies);

        String laravel_session="";
        for (int i = 0; i < cookies.size(); i++) {
//            System.out.println(cookies.get(i));
            if(i>=2){
                laravel_session+=cookies.get(i);
            }
        }
        // Print the cookies
//        System.out.println("Cookies: " + cookies);
        System.out.println(laravel_session);
        for (int i = 4; i >= 1; i--) {
            apiService.callApiWithCookies(laravel_session,Integer.toString(i));
        }

    }
    // Helper method to extract the value of a specific cookie
    private static String extractCookieValue(String cookies, String cookieName) {
        if (cookies != null && cookieName != null) {
            String[] cookieParts = cookies.split(";");
            for (String cookiePart : cookieParts) {
                String[] nameValue = cookiePart.trim().split("=");
                if (nameValue.length == 2 && cookieName.equals(nameValue[0])) {
                    return nameValue[1];
                }
            }
        }
        return null;
    }
}