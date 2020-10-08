package com.example.demo.service;

import cn.hutool.core.lang.Validator;
import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtUser;
import com.example.demo.entity.UserAccount;
import com.example.demo.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.RandomUtil;

import javax.annotation.Resource;

/**
 * JwtUserDetailsService
 * 实现UserDetailsService,重写loadUserByUsername方法
 * 返回随机生成的user,pass是密码,这里固定生成的
 * 如果你自己需要定制查询user的方法,请改造这里
 *
 * @author zhengkai.blog.csdn.net
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Resource
    UserAccountMapper userAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
//        UserAccount userAccount=userAccountMapper.getUserByUserAccount(username);
//        if (userAccount!=null){
//            return new JwtUser(userAccount.getId().toString(),userAccount.getUserAccount(),userAccount.getSecurityCode(),userAccount.getOperationCode(),true);
//        }else{
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        }

        UserAccount userAccount = userAccountMapper.getUserByUserAccount(username);

        if (Validator.isNotEmpty(userAccount) && Validator.isNotEmpty(username) && userAccount.getUserAccount().contains(username)) {
            return new JwtUser(userAccount.getId().toString(), username, userAccount.getSecurityCode(), userAccount.getOperationCode(), true);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }
}