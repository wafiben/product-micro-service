package org.example.productmanagment.application.port.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HealthController {
    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/swagger-ui/index.html");
    }
}