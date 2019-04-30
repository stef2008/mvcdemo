package com.nowcoder.mvcdemo.mapper;

import com.nowcoder.mvcdemo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {

    List<Map<String, Object>> selectByLevel();

    List<Category> selectAll();

}
