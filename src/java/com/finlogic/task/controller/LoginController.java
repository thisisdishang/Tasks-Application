/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finlogic.task.controller;

/**
 *
 * @author disha
 */
import com.finlogic.task.model.TasksUser;
import com.finlogic.task.service.UserService;

import com.finlogic.task.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login.fin")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role) {
        TasksUser user = userService.authenticate(username, password, role);

        if (user != null) {
            SessionUtil.setSessionAttribute(request, "loggedInUser", user);
            modelAndView.setViewName("redirect:task.fin?cmdAction=getTasks");
        } else {
            modelAndView.setViewName("welcome");
            modelAndView.addObject("error", "Invalid Credentials!");
        }
        return modelAndView;
    }

    @RequestMapping(params = "cmdAction=logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:task.fin?cmdAction=welcome";
    }
}
