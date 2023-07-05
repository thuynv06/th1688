package com.mkyong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.List;


@RestController
@RequestMapping("th1688")
public class ApiController {

    @Autowired
    private ApiService apiService;

//    @GetMapping("api/{pages}/{cookies}")
//    public ResponseEntity<Void> runApi(@PathVariable("pages") int pages,@PathVariable("cookies") String cookies){
////        int numberPages = 5;
////        String cookies= "laravel_session=eyJpdiI6IkpxTmE5SndOVGhMcGwyMFJ2N1pnbnc9PSIsInZhbHVlIjoiVHl6ZEJ0U1J1eU1PQWhRVldkaVY0cmw4ZXFRVVdQcGdxUTNwTStLY0phdngxaGxZY0pxaytVeGtqMTgxNTdyWTk5elwvNU1Kb09nam0zSXYydVg2T2R3PT0iLCJtYWMiOiI4MTBlMWUyN2M3OWE2NTk1MWZmMWZlNzA0ZTdkOTZiOGIzYWIwNTY2YjY0ZDgwYjc2ZGUyYjdiMjVlMjMyMGJmIn0%3D;";
//        String logMessage = "runApi";
//        System.out.println(logMessage); // Output the log message to console
//
//        for (int i = pages; i >= 1; i--) {
//            apiService.callApiWithCookies(cookies,Integer.toString(i));
//        }
//        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
//        responseBuilder.header("Log-Message", logMessage);
//        return responseBuilder.build();
//    }
    @GetMapping("/{pages}/{cookies}")
    public ResponseEntity<Void> runApiToCloneData(@PathVariable("pages") int pages,@PathVariable("cookies") String cookies){
//        int numberPages = 5;
//        String cookies= "laravel_session=eyJpdiI6IkpxTmE5SndOVGhMcGwyMFJ2N1pnbnc9PSIsInZhbHVlIjoiVHl6ZEJ0U1J1eU1PQWhRVldkaVY0cmw4ZXFRVVdQcGdxUTNwTStLY0phdngxaGxZY0pxaytVeGtqMTgxNTdyWTk5elwvNU1Kb09nam0zSXYydVg2T2R3PT0iLCJtYWMiOiI4MTBlMWUyN2M3OWE2NTk1MWZmMWZlNzA0ZTdkOTZiOGIzYWIwNTY2YjY0ZDgwYjc2ZGUyYjdiMjVlMjMyMGJmIn0%3D;";
        String logMessage = "Log message to be shown in the response body";
        System.out.println(logMessage); // Output the log message to console

        for (int i = pages; i >= 1; i--) {
            apiService.callApiWithCookies(cookies,Integer.toString(i));
        }
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        responseBuilder.header("Log-Message", logMessage);
        return responseBuilder.build();
    }




}
