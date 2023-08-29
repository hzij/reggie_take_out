package com.hzj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzj.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * PackageName :com.hzj.mapper
 * ClassName: DishMapper
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/27  15:51
 * @edition 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
