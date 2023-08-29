package com.hzj.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * PackageName :com.hzj.pojo
 * ClassName: Employee
 * Description:
 *  员工实体
 * @Author 郝紫俊
 * @Create 2023/8/16  10:22
 * @edition 1.0
 */
@Data
public class Employee {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    private String idNumber;//身份证号码

    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;//创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;//更新时间

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;//创建人

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;//修改人
}
