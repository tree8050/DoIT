package com.doit.study.wishlist.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WishListController {

    @PostMapping("/like")
    @ResponseBody
    public String like(@RequestParam Map<String,Object> commandMap) {
        log.info("commandMap={}", commandMap);
        return "ok";
    }
}
