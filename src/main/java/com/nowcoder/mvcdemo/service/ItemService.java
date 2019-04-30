package com.nowcoder.mvcdemo.service;

import com.nowcoder.mvcdemo.entity.Item;
import com.nowcoder.mvcdemo.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    public List<Item> findItemByPage(int page, int limit) {
        if (page <= 0 || limit <= 0) {
            throw new RuntimeException("参数不合法！");
        }

        int offset = (page - 1) * limit;
        return itemMapper.selectByPage(offset, limit);
    }

    public int findItemCount() {
        return itemMapper.selectCount();
    }

    public void add(Item item) {
        itemMapper.insert(item);
    }

    public Item findById(int id) {
        return itemMapper.selectById(id);
    }

}
