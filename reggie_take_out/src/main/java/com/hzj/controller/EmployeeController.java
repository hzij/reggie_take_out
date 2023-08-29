package com.hzj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzj.common.R;
import com.hzj.pojo.Employee;
import com.hzj.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * PackageName :com.hzj.controller
 * ClassName: EmployeeController
 * Description:
 *
 * @Author 郝紫俊
 * @Create 2023/8/16  10:31
 * @edition 1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        /**1、将页面提交的密码password进行md5加密处理
         * 2、根据页面提交的用户名username查询数据库
         * 3、如果没有查询到则返回登录失败结果
         * 4、密码比对，如果不一致则返回登录失败结果
         * 5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
         * 6、登录成功，将员工id存入Session并返回登录成功结果
         */
        //1.将页面提交的数据做MD5处理
        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        // 2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> elqw = new LambdaQueryWrapper<>();
        elqw.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(elqw);
        //3、如果没有查询到则返回登录失败结果
        if (one==null){
            return R.error("登录失败");
        }
        // 4、密码比对，如果不一致则返回登录失败结果
        if (!one.getPassword().equals(password)){
            return R.error("登陆失败");
        }
        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (one.getStatus()==0){
            return R.error("状态已禁用");
        }
        //6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee",one.getId());
        return R.success(one);
    }

    /**
     * 员工退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee) {
        log.info("员工信息：{}",employee.toString());

        //设置MD5处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //设置创建员工的时间
//        employee.setCreateTime(LocalDateTime.now());
        //设置员工的更新时间
//        employee.setUpdateTime(LocalDateTime.now());

        //获得当前用户的id
//        Long empId=(Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);
        //调用save方法添加数据
        employeeService.save(employee);
        log.info("数据添加成功，添加的用户id为{}:",employee.getId());
        return R.success("新增员工成功");
    }


    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo=new Page<>(page,pageSize);
        //分页配置
        LambdaQueryWrapper<Employee> queryWrapper = employeeService.page(page, pageSize, name);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        employee.setUpdateTime(LocalDateTime.now());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }
    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long  id){
        log.info("根据查询员工信息");
        Employee employee=employeeService.getById(id);
        if (employee!=null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工");
    }
 }
