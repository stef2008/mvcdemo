package com.nowcoder.mvcdemo.controller;

import com.nowcoder.mvcdemo.entity.User;
import com.nowcoder.mvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toregister")
    public ModelAndView toRegister(String message) {
//        Integer.valueOf("hello");
        ModelAndView mav = new ModelAndView();
        if (!StringUtils.isEmpty(message)) {
            mav.addObject("message", message);
        }
        mav.setViewName("register");
        return mav;
    }

    @RequestMapping("/tologin")
    public ModelAndView toLogin(String message) {
        ModelAndView mav = new ModelAndView();
        if (!StringUtils.isEmpty(message)) {
            mav.addObject("message", message);
        }
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/register")
    public ModelAndView register(User user) {
        ModelAndView mav = new ModelAndView();
        boolean success = userService.register(user);
        if (success) {
            mav.setViewName("redirect:/user/tologin");
        } else {
            mav.addObject("message", "账号已存在！");
            mav.setViewName("redirect:/user/toregister");
        }
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        boolean success = userService.login(username, password);
        if (success) {
            session.setAttribute("username", username);
            mav.setViewName("redirect:/");
        } else {
            mav.addObject("message", "账号或密码错误！");
            mav.setViewName("redirect:/user/tologin");
        }
        return mav;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/list")
    public ModelAndView list() {
        List<User> list = userService.findAllUsers();

        ModelAndView mav = new ModelAndView();
        mav.addObject("users", list);
        mav.setViewName("user-list");
        return mav;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("id") int id) {
        User user = userService.findUser(id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("user-detail");
        return mav;
    }

    @RequestMapping("/add")
    public String add() {
        return "user-add";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user) {
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam("id") int id) {
        User user = userService.findUser(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("user-update");
        return mav;
    }

}
