package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel(value = "用户帐号信息实体", description = "用户帐号信息实体")
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userAccount;

    private String securityCode;

    private String operationCode;

    private Long createAt;

    private Long updateAt;

    @TableField(exist = false)
    private String captchaCode;

    @TableField(exist = false)
    private String rd;
}
