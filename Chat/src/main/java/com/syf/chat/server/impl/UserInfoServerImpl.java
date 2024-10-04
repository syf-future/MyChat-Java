package com.syf.chat.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syf.chat.common.enums.EnumAddState;
import com.syf.chat.common.enums.EnumLoginState;
import com.syf.chat.common.enums.EnumReturnStatus;
import com.syf.chat.common.enums.EnumSendType;
import com.syf.chat.common.utils.SequenceTool;
import com.syf.chat.entity.dto.FriendInfoDto;
import com.syf.chat.entity.dto.LoginDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.FriendShipsDo;
import com.syf.chat.entity.model.MessageInfoDo;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.entity.model.UserOperateDo;
import com.syf.chat.entity.vo.ClickFriendVo;
import com.syf.chat.entity.vo.LoginVo;
import com.syf.chat.entity.vo.RegisterVo;
import com.syf.chat.mapper.FriendShipsMapper;
import com.syf.chat.mapper.UserInfoMapper;
import com.syf.chat.mapper.UserOperateMapper;
import com.syf.chat.server.UserInfoServer;
import com.syf.chat.server.handler.MessageHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserInfoServerImpl implements UserInfoServer {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private FriendShipsMapper friendShipsMapper;
    @Resource
    private MessageHandler messageHandler;
    @Resource
    private UserOperateMapper userOperateMapper;

    /**
     * 用户登录
     *
     * @param loginVo 登录信息
     * @return 用户信息
     */
    @Override
    public R<LoginDto> login(LoginVo loginVo) {
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
        LoginDto loginDto = new LoginDto();
        BeanUtils.copyProperties(userInfoDo, loginDto);
        return R.ok(loginDto);
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

        UserOperateDo userOperateDo = new UserOperateDo();
        userOperateDo.setSerialNo(userInfoDo.getSerialNo());
        userOperateDo.setLoginState(EnumLoginState.UN_LOGIN.getCode());
        userOperateDo.setOperateState("0");
        userOperateDo.setCreateTime(new Date());
        userOperateDo.setUpdateTime(new Date());
        userOperateMapper.insert(userOperateDo);
        userInfoMapper.insert(userInfoDo);
        return R.ok(true);
    }

    /**
     * 登录成功
     *
     * @param serialNo 用户流水号
     * @return
     */
    @Override
    public R<List<FriendInfoDto>> loginSuccess(String serialNo) {
        UserOperateDo userOperateDo = new UserOperateDo();
        userOperateDo.setSerialNo(serialNo);
        userOperateDo.setLoginState(EnumLoginState.LOGIN.getCode());
        userOperateDo.setUpdateTime(new Date());
        userOperateMapper.updateById(userOperateDo);
        log.info("开始查询用户：{} 的好友", serialNo);
        LambdaQueryWrapper<FriendShipsDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FriendShipsDo::getSendType, EnumSendType.FRIEND.getCode())
                .eq(FriendShipsDo::getStatus, EnumAddState.SUCCESS.getCode())
                .and(wrapper -> wrapper.eq(FriendShipsDo::getUserId, serialNo)
                        .or()
                        .eq(FriendShipsDo::getFriendId, serialNo))
                .orderByAsc(FriendShipsDo::getUpdateTime);
        List<FriendShipsDo> friendShipsDos = friendShipsMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(friendShipsDos)) {
            log.info("{}用户好友为空", serialNo);
            return null;
        }
        List<String> friendIds = friendShipsDos.stream()
                .flatMap(friendShipsDo -> Stream.of(
                        friendShipsDo.getUserId(),
                        friendShipsDo.getFriendId()
                )) // 将 userId 和 friendId 转换为流
                .filter(userId -> !userId.equals(serialNo)) // 过滤出等于 serialNo 的值
                .toList(); // 收集到 List<String> 中
        log.info("共有{}个好友:{}",friendIds.size(),friendIds);
        log.info("开始构建好友信息");
        LambdaQueryWrapper<UserInfoDo> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserInfoDo::getSerialNo, friendIds);
        List<UserInfoDo> userInfoDos = userInfoMapper.selectList(wrapper);
        List<FriendInfoDto> friendInfoDtoList = new ArrayList<>();
        userInfoDos.forEach(userInfoDo -> {
            FriendInfoDto friendInfoDto = new FriendInfoDto();
            ClickFriendVo clickFriendVo = new ClickFriendVo();
            clickFriendVo.setUserId(serialNo);
            clickFriendVo.setFriendId(userInfoDo.getSerialNo());
            List<MessageInfoDo> messageInfoDos = messageHandler.getTextMessage(clickFriendVo);
            friendInfoDto.setUnreadNum(String.valueOf(0));
            if(!CollectionUtils.isEmpty(messageInfoDos)){
                int num = messageInfoDos.stream()
                        .filter(messageInfoDo -> messageInfoDo.getFriendId().equals(serialNo)  // friendId 与 serialNo 相等
                                && messageInfoDo.getIsRead().equals("0"))        // isRead 为 "0"
                        .toList().size();
                friendInfoDto.setUnreadNum(String.valueOf(num));
                //获取最后一天信息
                friendInfoDto.setLastMessage(messageInfoDos.get(messageInfoDos.size()-1).getContent());
                friendInfoDto.setLastMessageTime(messageInfoDos.get(messageInfoDos.size()-1).getUpdateTime());
            }
            friendInfoDto.setFriendId(userInfoDo.getSerialNo());
            friendInfoDto.setUserName(userInfoDo.getUserName());
            friendInfoDto.setPicture(userInfoDo.getPicture());
            friendInfoDtoList.add(friendInfoDto);
        });
        return R.ok(friendInfoDtoList);
    }
}
