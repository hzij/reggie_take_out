package com.hzj.dto;

import com.hzj.pojo.Dish;

import com.hzj.pojo.DishFlavor;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
