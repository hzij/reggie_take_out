package com.hzj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzj.dto.DishDto;
import com.hzj.mapper.DishMapper;
import com.hzj.pojo.Dish;

/**
 * PackageName :com.hzj.service
 * ClassName: DishService
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/27  15:51
 * @edition 1.0
 */
public interface DishService  extends IService<Dish> {
    //新增彩票，同时插入菜品对应的口味数据，需要操作两张表dish，dishFlavor
    void saveWithFlavor(DishDto dto);
}
