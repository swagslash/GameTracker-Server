package io.swagslash.gametrackerserver.endpoint.agent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @RequestMapping({"/", ""})
    public String index() {
        return "test";
    }

}
