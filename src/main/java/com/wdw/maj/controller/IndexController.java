package com.wdw.maj.controller;

import com.wdw.maj.dto.PaginationDto;
import com.wdw.maj.mapper.UserMapper;
import com.wdw.maj.model.User;
import com.wdw.maj.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDto paginationDtos = questionService.getQuestionList(page, size);
        model.addAttribute("question", paginationDtos);
        return "index";
    }
}
