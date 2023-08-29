package com.hzj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzj.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * PackageName :com.hzj.mapper
 * ClassName: EmployeeMapper
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/16  10:27
 * @edition 1.0
 */
@Mapper
public interface EmployeeMapper  extends BaseMapper<Employee> {
}
