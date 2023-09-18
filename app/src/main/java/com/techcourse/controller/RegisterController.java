package com.techcourse.controller;

import com.techcourse.domain.User;
import com.techcourse.repository.InMemoryUserRepository;
import context.org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.org.springframework.web.bind.annotation.RequestMapping;
import web.org.springframework.web.bind.annotation.RequestMethod;
import webmvc.org.springframework.web.servlet.ModelAndView;
import webmvc.org.springframework.web.servlet.view.JspView;

@Controller
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView show(final HttpServletRequest req, final HttpServletResponse res) {
        final String redirectViewName = "redirect:register.jsp";

        return new ModelAndView(new JspView(redirectViewName));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(final HttpServletRequest req, final HttpServletResponse res) {
        final String redirectViewName = "redirect:/index.jsp";
        saveUser(req);

        return new ModelAndView(new JspView(redirectViewName));
    }

    private void saveUser(final HttpServletRequest request) {
        final var user = new User(
            request.getParameter("account"),
            request.getParameter("password"),
            request.getParameter("email")
        );
        InMemoryUserRepository.save(user);
        log.debug("User Registered : {}", user);
    }
}
