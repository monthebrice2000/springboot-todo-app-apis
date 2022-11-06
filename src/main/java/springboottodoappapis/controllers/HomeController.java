package springboottodoappapis.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @PreAuthorize("hasRole(\"ADMIN\")")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
