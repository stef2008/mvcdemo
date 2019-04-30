package com.nowcoder.mvcdemo.controller;

import com.nowcoder.mvcdemo.entity.Demo;
import com.nowcoder.mvcdemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {

    // 1.响应的方式

    @RequestMapping("/index")
    public String index() {
        return "/demo/index";
    }

    @RequestMapping("/response2")
    public ModelAndView response2() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("caocao");
        demo.setSalary(8000.00);
        demo.setCreateTime(new Timestamp(System.currentTimeMillis()));

        ModelAndView mav = new ModelAndView();
        mav.addObject("demo", demo);
        mav.addObject("limit", 10);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping("/response3")
    @ResponseBody
    public Double response3() {
        return 3.1415926;
    }

    @RequestMapping("/response4")
    @ResponseBody
    public Demo response4() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("caocao");
        demo.setSalary(8000.00);
        demo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return demo;
    }

    @RequestMapping("/response5")
    @ResponseBody
    public List<Demo> response5() {
        List<Demo> list = new ArrayList<>();
        Demo demo1 = new Demo();
        demo1.setId(1);
        demo1.setName("caocao");
        demo1.setSalary(8000.00);
        demo1.setCreateTime(new Timestamp(System.currentTimeMillis()));
        list.add(demo1);
        Demo demo2 = new Demo();
        demo2.setId(2);
        demo2.setName("xuchu");
        demo2.setSalary(7000.00);
        demo2.setCreateTime(new Timestamp(System.currentTimeMillis()));
        list.add(demo2);
        Demo demo3 = new Demo();
        demo3.setId(3);
        demo3.setName("xiahoudun");
        demo3.setSalary(6000.00);
        demo3.setCreateTime(new Timestamp(System.currentTimeMillis()));
        list.add(demo3);
        return list;
    }

    @RequestMapping("/response6")
    public String response6() {
        return "redirect:/demo/index";
    }

    // 2.获取请求的参数

    @RequestMapping("/params1")
    public String params1(Integer id, String name) {
        System.out.println(id);
        System.out.println(name);
        return "/demo/view";
    }

    @RequestMapping("/params2")
    public String params2(
            @RequestParam(value = "id", required = false, defaultValue = "1") Integer id,
            @RequestParam(value = "name", required = false, defaultValue = "ADMIN") String name) {
        System.out.println(id);
        System.out.println(name);
        return "/demo/view";
    }

    @RequestMapping("/form")
    public String form() {
        return "/demo/form";
    }

    @RequestMapping("/params3")
    public String params3(Demo demo) {
        System.out.println(demo);
        return "/demo/view";
    }

    @RequestMapping("/json")
    public String json() {
        return "/demo/json";
    }

    @RequestMapping("/params4")
    @ResponseBody
    public String params4(@RequestBody Demo demo) {
        System.out.println(demo);
        return "ok";
    }

    // 异步的表单提交
    @RequestMapping("/params5")
    @ResponseBody
    public String params5(Demo demo) {
        System.out.println(demo);
        return "ok";
    }

    // 3.文件上传示例

    @RequestMapping("/toupload")
    public String toUploadPage() {
        return "/demo/upload";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("products") MultipartFile[] files) {
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (StringUtils.isEmpty(fileName)) {
                    continue;
                }
                File dest = new File(fileName);
                try {
                    file.transferTo(dest);
                    System.out.println(dest.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("文件上传失败！", e);
                }
            }
        }
        return "redirect:/demo/index";
    }


    // 4.ControllerAdvice

    @RequestMapping("/exp")
    public String testExption() {
        Integer.valueOf("hello");
        return "/demo/view";
    }

    @RequestMapping("/model")
    public String testModel(Model model) {
        model.addAttribute("companyName", "nowcoder");
        return "/demo/model";
    }

    @RequestMapping("/tobinder")
    public String toBinder() {
        return "/demo/user";
    }

    @RequestMapping("/binder")
    public ModelAndView binder(@Valid User user, Errors errors) {
        ModelAndView mav = new ModelAndView();
        if (errors.hasErrors()) {
            mav.addObject("message", "请输入合法的参数");
            mav.setViewName("/demo/user");
        } else {
            mav.setViewName("/demo/index");
        }
        return mav;
    }

    // 5.Cookie和Session

    @RequestMapping("/login1")
    public String login1(String username, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "/demo/index";
    }

    @RequestMapping("/list1")
    public String list1(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        return "/demo/list";
    }

    @RequestMapping("/login2")
    public String login2(String username, HttpSession session) {
        session.setAttribute("username", username);

        return "/demo/index";
    }

    @RequestMapping("/list2")
    public String list2(HttpSession session) {
        System.out.println(session.getAttribute("username"));
        return "/demo/list";
    }

    @RequestMapping("/log")
    public String log() {
        Logger logger = LoggerFactory.getLogger(DemoController.class);
        logger.trace("-----> trace");
        logger.debug("-----> debug");
        logger.info("-----> info");
        logger.warn("-----> warn");
        logger.error("-----> error");

        System.out.println(logger.getClass().getName());

        return "/demo/index";
    }

}
