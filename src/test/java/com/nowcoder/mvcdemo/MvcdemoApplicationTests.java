package com.nowcoder.mvcdemo;

import com.nowcoder.mvcdemo.entity.Category;
import com.nowcoder.mvcdemo.entity.Item;
import com.nowcoder.mvcdemo.entity.User;
import com.nowcoder.mvcdemo.mapper.UserMapper;
import com.nowcoder.mvcdemo.service.CategoryService;
import com.nowcoder.mvcdemo.service.ItemService;
import com.nowcoder.mvcdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MvcdemoApplication.class})
public class MvcdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @Test
    public void testSelectAllUser() {
        List<User> list = userMapper.selectAll();
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testAspcet() {
        List<Category> categories = categoryService.findAll();
        for (Category category : categories) {
            System.out.println(category);
        }

        List<Item> items = itemService.findItemByPage(-1, -1);
        for (Item item : items) {
            System.out.println(item);
        }
    }

}
