package com.example.cipipeline;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AzureDevOps {
    @GetMapping("/azure-devops")
    public String getData(){
        return "AZURE DEVOPS";
    }
}
