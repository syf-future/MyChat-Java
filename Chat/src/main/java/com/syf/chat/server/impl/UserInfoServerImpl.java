package com.syf.chat.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syf.chat.common.enums.EnumReturnStatus;
import com.syf.chat.common.utils.SequenceTool;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.entity.vo.LoginVo;
import com.syf.chat.entity.vo.RegisterVo;
import com.syf.chat.mapper.UserInfoMapper;
import com.syf.chat.server.UserInfoServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserInfoServerImpl implements UserInfoServer {
    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 用户登录
     *
     * @param loginVo 登录信息
     * @return 用户信息
     */
    @Override
    public R<UserInfoDo> login(LoginVo loginVo) {
        log.info("开始用户登录，请求参数：{}", loginVo);
        if (null == loginVo) {
            log.error("参数异常");
            return R.fail(EnumReturnStatus.PARAM_ERROR);
        }
        LambdaQueryWrapper<UserInfoDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoDo::getAccount, loginVo.getAccount())
                .eq(UserInfoDo::getPassword, loginVo.getPassword());
        UserInfoDo userInfoDo = userInfoMapper.selectOne(queryWrapper);
        if (null == userInfoDo) {
            log.error("账号或密码错误：{}", loginVo);
            return R.fail(EnumReturnStatus.USERINFO_ERROR);
        }
        log.info("用户登录成功，返回用户信息：{}", userInfoDo);
        return R.ok(userInfoDo);
    }

    /**
     * 用户注册
     *
     * @param registerVo 注册信息
     * @return bool
     */
    @Override
    public R<Boolean> register(RegisterVo registerVo) {
        log.info("开始用户注册，请求参数：{}", registerVo);
        LambdaQueryWrapper<UserInfoDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoDo::getAccount, registerVo.getAccount());
        Long count = userInfoMapper.selectCount(queryWrapper);
        if (count > 0) {
            log.error("账号已存在：{}", registerVo);
            return R.fail(EnumReturnStatus.USER_EXIST);
        }
        UserInfoDo userInfoDo = new UserInfoDo();
        userInfoDo.setSerialNo(SequenceTool.nextId());
        userInfoDo.setAccount(registerVo.getAccount());
        userInfoDo.setPassword(registerVo.getPassword());
        userInfoDo.setPhone(registerVo.getPhone());
        userInfoDo.setUserName(registerVo.getAccount());
        userInfoDo.setCreateTime(new Date());
        userInfoDo.setUpdateTime(new Date());
        userInfoMapper.insert(userInfoDo);
        return R.ok(true);
    }
}
