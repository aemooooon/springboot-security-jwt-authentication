package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserAccount;
import org.apache.ibatis.annotations.Select;


public interface UserAccountMapper extends BaseMapper<UserAccount> {
    // 根据用户帐号查询用户信息 返回单个对象
    @Select("select * from user_account where binary user_account=#{user_account}")
    UserAccount getUserByUserAccount(String user_account);
}
