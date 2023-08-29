package com.hzj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzj.mapper.EmployeeMapper;
import com.hzj.pojo.Employee;
import com.hzj.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * PackageName :com.hzj.service.impl
 * ClassName: EmployeeServiceImpl
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/16  10:30
 * @edition 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public LambdaQueryWrapper<Employee> page(int page,int pageSize,String name) {
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = queryWrapper.orderByDesc(Employee::getUpdateTime);
        return queryWrapper;
    }
}
