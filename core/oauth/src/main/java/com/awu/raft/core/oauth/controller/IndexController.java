package com.awu.raft.core.oauth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LGP
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Value("from")
    private String value;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return String.format("Got value[$s] from config repository.", value);
    }

}
