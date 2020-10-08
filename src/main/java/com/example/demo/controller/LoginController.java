package com.example.demo.controller;


import javax.servlet.http.HttpServletRequest;

import com.example.demo.common.CommonResult;
import com.example.demo.common.ErrorCodeEnum;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conf.JwtTokenUtil;
import com.example.demo.entity.JwtRequest;
import com.example.demo.entity.JwtResponse;
import com.example.demo.entity.JwtUser;
import com.example.demo.service.JwtUserDetailsService;

/**
 * JwtAuthenticationController
 * 包含登陆和查看token的方法
 *
 * @author zhengkai.blog.csdn.net
 */
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public CommonResult createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("username:" + authenticationRequest.getUsername() + ",password:" + authenticationRequest.getPassword());
        if (!authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())) {
            return CommonResult.fail(ErrorCodeEnum.NOT_MATCH_USER_ACCOUNT_PASSWORD);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(userDetails.toString());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return CommonResult.success(new JwtResponse(token));
    }

    private boolean authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (DisabledException | BadCredentialsException e) {
            // throw new Exception("USER_DISABLED", e);
            // throw new Exception("Bad credentials", e);
            return false;
        }
    }

    @GetMapping("/token")
    public String getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        return jwtTokenUtil.getClaimFromToken(token, Claims::getId);
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        String id=jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
        return "Hello "+id+ " " + SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}