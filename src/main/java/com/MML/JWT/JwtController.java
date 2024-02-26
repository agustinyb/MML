package com.MML.JWT;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JwtController {

    @PostMapping(value = "/jwt")
    public String welcome() {
        return "Welcome from secure endpoint";
    }
}
