package com.nowcoder.mvcdemo.mapper;

import com.nowcoder.mvcdemo.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> selectByPage(int offset, int limit);

    int selectCount();

    void insert(Item item);

    Item selectById(int id);

}
