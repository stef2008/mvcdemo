package com.nowcoder.mvcdemo.service;

import com.nowcoder.mvcdemo.entity.Category;
import com.nowcoder.mvcdemo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Map<String, Object>> findAllLevelCategorys() {
        return categoryMapper.selectByLevel();
    }

    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

}
