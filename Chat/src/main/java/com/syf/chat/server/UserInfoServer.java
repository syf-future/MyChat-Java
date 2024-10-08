package com.syf.chat.server;

import com.syf.chat.entity.dto.FriendInfoDto;
import com.syf.chat.entity.dto.LoginDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.entity.vo.LoginVo;
import com.syf.chat.entity.vo.RegisterVo;

import java.util.List;

public interface UserInfoServer {
    /**
     * 用户登录
     * @param loginVo 登录信息
     * @return         用户信息
     */
    R<LoginDto> login(LoginVo loginVo);

    /**
     * 用户注册
     * @param registerVo 注册信息
     * @return           bool
     */
    R<Boolean> register(RegisterVo registerVo);

    /**
     * 登录成功事件
     * @param serialNo 用户流水号
     * @return
     */
    R<List<FriendInfoDto>> loginSuccess(String serialNo);

    /**
     * 退出登录
     * @param serialNo  用户号
     * @return          bool
     */
    R quitLogin(String serialNo);
}
