package com.deafio.gateway.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
	@GetMapping("/fallback")
    public List<?> fallback() {
        return new ArrayList<>();
    }
}
