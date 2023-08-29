package com.hzj.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzj.pojo.Employee;
import org.springframework.stereotype.Service;

/**
 * PackageName :com.hzj.service
 * ClassName: EmployeeService
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/16  10:28
 * @edition 1.0
 */

public interface EmployeeService extends IService<Employee> {
        LambdaQueryWrapper<Employee> page(int page,int pageSize,String name);
}
