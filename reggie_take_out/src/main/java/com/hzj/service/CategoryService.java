package com.hzj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzj.pojo.Category;

/**
 * PackageName :com.hzj.service
 * ClassName: CategoryService
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/19  16:52
 * @edition 1.0
 */
public interface CategoryService extends IService<Category> {
     void remove(Long id);
}
