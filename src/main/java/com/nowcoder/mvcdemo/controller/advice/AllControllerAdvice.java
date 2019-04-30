package com.nowcoder.mvcdemo.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(
        basePackages = {"com.nowcoder.mvcdemo.controller.*"},
        annotations = {Controller.class})
public class AllControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.setViewName("/error/500");
        return mav;
    }

    @ModelAttribute
    public void projectName(ModelMap model) {
//        model.addAttribute("projectName", "mvcdemo");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        binder.setValidator(new UserValidator());
    }

}
