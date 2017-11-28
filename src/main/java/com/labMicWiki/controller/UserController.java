package com.labMicWiki.controller;
import com.labMicWiki.domain.User;
import com.labMicWiki.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
@Controller
public class UserController {
   
   @Autowired
   private UserService userService;

   @RequestMapping(value = "/LogIn", method = RequestMethod.GET)
   public ModelAndView logIn() {
      return new ModelAndView("user", "user", new User());
   }

   @RequestMapping(value = "/postUsername", method = RequestMethod.POST)
   public ModelAndView  postUsername(@ModelAttribute("user") User user, HttpServletResponse response) {
      String view = "user";

      ModelAndView modelAndView = new ModelAndView();

      if (userService.checkAccess(user)) {
         response.addCookie(new Cookie("nickName", user.getNickName()));
         view = "redirect:/";
      }
      else modelAndView.addObject("error", "Login or password is wrong");

      modelAndView.setViewName(view);

      return modelAndView;
   }

}