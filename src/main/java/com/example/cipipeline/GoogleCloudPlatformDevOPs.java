package com.example.cipipeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoogleCloudPlatformDevOPs {
    @GetMapping("/gcp")
    public String getData(){
        return "GOOGLE CLOUD PLATFORM GCP - DEVOPS";
    }
}
