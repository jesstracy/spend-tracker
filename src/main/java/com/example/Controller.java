package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jessicatracy on 2/5/17.
 */
@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
