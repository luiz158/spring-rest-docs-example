package com.springrestdocs;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/users")
public class V2Controller extends V1Controller {

    @PutMapping
    public String updateUser(User user) {
        return "OK";
    }

}
