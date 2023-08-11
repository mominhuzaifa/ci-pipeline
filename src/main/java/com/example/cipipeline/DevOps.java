package com.example.cipipeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DevOps {
    @GetMapping("/devops")
    public String getData(){
        return "DEVOPS - JENKINS - DOCKER - TERRAFORM - NEXUS - AMAZON S3 - ECR - DEVOPS";
    }
}
