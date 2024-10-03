package com.syf.chat.controller;

import com.syf.chat.entity.dto.FriendInfoDto;
import com.syf.chat.entity.dto.LoginDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.entity.vo.LoginVo;
import com.syf.chat.entity.vo.RegisterVo;
import com.syf.chat.server.UserInfoServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat/user")
public class LoginController {
    @Resource
    private UserInfoServer userInfoServer;

    /**
     * 用户登录
     * @param loginVo 登录信息
     * @return 用户信息
     */
    @PostMapping("/login")
    public R<LoginDto> login(@RequestBody LoginVo loginVo) {
        return userInfoServer.login(loginVo);
    }

    /**
     * 新用户注册
     * @param registerVo 登录信息
     * @return 用户信息
     */
    @PostMapping("/register")
    public R<Boolean> register(@RequestBody RegisterVo registerVo) {
        return userInfoServer.register(registerVo);
    }

    /**
     * 登录成功事件
     *
     * @param serialNoMap 用户信息流水号
     * @return 好友信息
     */
    @PostMapping("/loginSuccess")
    public R<List<FriendInfoDto>> loginSuccess(@RequestBody Map<String, String> serialNoMap) {
        return userInfoServer.loginSuccess(serialNoMap.get("serialNo"));
    }
}
