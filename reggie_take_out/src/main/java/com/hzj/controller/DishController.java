package com.hzj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzj.common.R;
import com.hzj.dto.DishDto;
import com.hzj.pojo.Dish;
import com.hzj.pojo.DishFlavor;
import com.hzj.service.DishFlavorService;
import com.hzj.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PackageName :com.hzj.controller
 * ClassName: DishController
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/28  16:10
 * @edition 1.0
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dto){
        log.info(dto.toString());
        dishService.saveWithFlavor(dto);
        return R.success("新增菜品成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page ,int pageSize,String name){
        //构造分页器
        Page<Dish> pageInfo =new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper();

        //添加过滤条件、
        queryWrapper.like(name!=null,Dish::getName,name);
        //添加排序条件
        queryWrapper.orderByAsc(Dish::getUpdateTime);

        dishService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);

    }
}
