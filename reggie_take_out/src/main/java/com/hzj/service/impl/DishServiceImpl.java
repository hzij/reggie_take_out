package com.hzj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzj.dto.DishDto;
import com.hzj.mapper.DishMapper;
import com.hzj.pojo.Dish;
import com.hzj.pojo.DishFlavor;
import com.hzj.service.DishFlavorService;
import com.hzj.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * PackageName :com.hzj.service.impl
 * ClassName: DishServiceImpl
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/27  15:53
 * @edition 1.0
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * 新增菜品保存对应的口味数据
     * @param dto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dto);

        Long dishId = dto.getId();//菜品ID
        //菜品口味
        List<DishFlavor> flavors = dto.getFlavors();//
        //不会
        flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

       dishFlavorService.saveBatch(flavors);

    }
}
