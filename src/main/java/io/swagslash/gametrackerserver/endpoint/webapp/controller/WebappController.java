package io.swagslash.gametrackerserver.endpoint.webapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webapp")
public class WebappController {

    @RequestMapping({"/", ""})
    public String index() {
        return "test";
    }

}
