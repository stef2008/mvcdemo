package com.nowcoder.mvcdemo.controller;

import com.nowcoder.mvcdemo.entity.Category;
import com.nowcoder.mvcdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ModelAndView list() {
        List<Map<String, Object>> list = categoryService.findAllLevelCategorys();
        ModelAndView mav = new ModelAndView();
        mav.addObject("categorys", list);
        mav.setViewName("/category/list");
        return mav;
    }

    @RequestMapping("all")
    @ResponseBody
    public List<Category> all() {
        return categoryService.findAll();
    }

}
