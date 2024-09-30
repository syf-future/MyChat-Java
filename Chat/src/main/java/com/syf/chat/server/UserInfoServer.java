package com.syf.chat.server;

import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.entity.vo.LoginVo;
import com.syf.chat.entity.vo.RegisterVo;

public interface UserInfoServer {
    /**
     * 用户登录
     * @param loginVo 登录信息
     * @return         用户信息
     */
    R<UserInfoDo> login(LoginVo loginVo);

    /**
     * 用户注册
     * @param registerVo 注册信息
     * @return           bool
     */
    R<Boolean> register(RegisterVo registerVo);
}
