package com.springrestdocs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class V1Controller {

    @PostMapping
    public String createUser(@RequestBody User user) {
        return "OK";
    }

}
