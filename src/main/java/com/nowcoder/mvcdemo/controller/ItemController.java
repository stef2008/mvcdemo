package com.nowcoder.mvcdemo.controller;

import com.nowcoder.mvcdemo.entity.Item;
import com.nowcoder.mvcdemo.service.CategoryService;
import com.nowcoder.mvcdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        List<Item> list = itemService.findItemByPage(page, limit);
        int count = itemService.findItemCount();

        int total = count % limit == 0 ? count / limit : count / limit + 1;

        ModelAndView mav = new ModelAndView();
        mav.addObject("items", list);
        mav.addObject("page", page);
        mav.addObject("total", total);
        mav.setViewName("/item/list");
        return mav;
    }

    @RequestMapping("/toadd")
    public ModelAndView toAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/item/add");
        return mav;
    }

    @RequestMapping("/add")
    public String add(Item item, @RequestParam("productImage") MultipartFile[] files) {
        String path = null;
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (StringUtils.isEmpty(fileName)) {
                    continue;
                }
                File dest = new File(fileName);
                try {
                    file.transferTo(dest);
                    if (path == null) {
                        path = dest.getName();
                    } else {
                        path += "," + dest.getName();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("文件上传失败！", e);
                }
            }
        }
        item.setImage(path);
        itemService.add(item);
        return "redirect:/item/list";
    }

    @RequestMapping("/detail")
    public ModelAndView detail(int id) {
        Item item = itemService.findById(id);

        ModelAndView mav = new ModelAndView();
        mav.addObject("item", item);
        mav.setViewName("/item/detail");
        return mav;
    }

    @Value("${spring.servlet.multipart.location}")
    private String imgPath;

    @RequestMapping("/img")
    public void imgs(String name, HttpServletResponse response) {
        String fileName = imgPath + "/" + name;
        File file = new File(fileName);
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[10240];
            int b = 0;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件失败！", e);
        }
    }

}
