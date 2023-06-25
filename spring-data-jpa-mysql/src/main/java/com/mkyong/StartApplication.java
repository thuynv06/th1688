package com.mkyong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;

import java.util.List;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    //    @Autowired
//    private BookRepository repository;
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private KienHangRepository kienHangRepository;

    @Autowired
    private ApiService apiService;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);
//        ApiService apiService = context.getBean(ApiService.class);
//        apiService.callApi();
//        int numberPages = 5;
//        String cookies= "laravel_session=eyJpdiI6IkpxTmE5SndOVGhMcGwyMFJ2N1pnbnc9PSIsInZhbHVlIjoiVHl6ZEJ0U1J1eU1PQWhRVldkaVY0cmw4ZXFRVVdQcGdxUTNwTStLY0phdngxaGxZY0pxaytVeGtqMTgxNTdyWTk5elwvNU1Kb09nam0zSXYydVg2T2R3PT0iLCJtYWMiOiI4MTBlMWUyN2M3OWE2NTk1MWZmMWZlNzA0ZTdkOTZiOGIzYWIwNTY2YjY0ZDgwYjc2ZGUyYjdiMjVlMjMyMGJmIn0%3D;";
//
//        for (int i = numberPages; i >= 1; i--) {
//            String page = Integer.toString(i);
//            apiService.callApiWithCookies(cookies,page);
//        }

        log.info("StartApplication...");

//        kienHangRepository.findAll().forEach(x -> System.out.println(x));


//        List<KienhangEntity> listK= kienHangRepository.findByMVD("JT3036068466554");
//
//        listK.forEach(x -> System.out.println(x));

//        userRepository.save(new UserEntity("Java"));
//        userRepository.save(new UserEntity("Node"));
//        userRepository.save(new UserEntity("Python"));

//        kienHangRepository.save(new KienhangEntity("mvd8778",BT))

//        System.out.println("\nfindAll()");
//        userRepository.findAll().forEach(x -> System.out.println(x));

//        System.out.println("\nfindById(1L)");
//        userRepository.findById(1l).ifPresent(x -> System.out.println(x));

//        System.out.println("\nfindByName('Node')");
//        repository.findByName("Node").forEach(x -> System.out.println(x));


    }

}