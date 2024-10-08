package com.syf.chat.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.syf.chat.common.enums.EnumAddState;
import com.syf.chat.common.enums.EnumSendType;
import com.syf.chat.common.utils.SequenceTool;
import com.syf.chat.entity.dto.AddFriendDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.FriendShipsDo;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.entity.vo.AddFriendVo;
import com.syf.chat.mapper.FriendShipsMapper;
import com.syf.chat.mapper.UserInfoMapper;
import com.syf.chat.server.AddFriendServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddFriendServerImpl implements AddFriendServer {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private FriendShipsMapper friendShipsMapper;

    /**
     * 添加好友
     *
     * @param addFriendVo
     * @return
     */
    @Override
    public R<List<AddFriendDto>> searchFriend(AddFriendVo addFriendVo) {
        LambdaQueryWrapper<UserInfoDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoDo::getAccount, addFriendVo.getUserId())
                .or()
                .eq(UserInfoDo::getUserName, addFriendVo.getUserId())
                .or()
                .eq(UserInfoDo::getPhone, addFriendVo.getUserId());
        List<UserInfoDo> userInfoDos = userInfoMapper.selectList(queryWrapper);
        List<AddFriendDto> addFriendDtos = new ArrayList<>();
        for (UserInfoDo userInfoDo : userInfoDos) {
            LambdaQueryWrapper<FriendShipsDo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(FriendShipsDo::getStatus, EnumAddState.SUCCESS.getCode())
                    .and(wrapper1 -> wrapper1.eq(FriendShipsDo::getUserId, addFriendVo.getSerialNo())
                            .eq(FriendShipsDo::getFriendId, userInfoDo.getSerialNo())
                            .or()
                            .eq(FriendShipsDo::getUserId, userInfoDo.getSerialNo())
                            .eq(FriendShipsDo::getFriendId, addFriendVo.getSerialNo()));
            Long num = friendShipsMapper.selectCount(wrapper);
            AddFriendDto addFriendDto = new AddFriendDto();
            addFriendDto.setSerialNo(userInfoDo.getSerialNo());
            addFriendDto.setAccount(userInfoDo.getAccount());
            addFriendDto.setUserName(userInfoDo.getUserName());
            addFriendDto.setPhone(userInfoDo.getPhone());
            addFriendDto.setPicture(userInfoDo.getPicture());
            if (num > 0 || addFriendVo.getSerialNo().equals(userInfoDo.getSerialNo())) {
                addFriendDto.setState(EnumAddState.SUCCESS.getCode());
            } else {
                addFriendDto.setState(EnumAddState.PASS.getCode());
            }
            addFriendDtos.add(addFriendDto);
        }
        return R.ok(addFriendDtos);
    }

    /**
     * 添加好友
     *
     * @param addFriendVo
     * @return
     */
    @Override
    public R addFriend(AddFriendVo addFriendVo) {
        FriendShipsDo friendShipsDo = new FriendShipsDo();
        friendShipsDo.setSerialNo(SequenceTool.nextId());
        friendShipsDo.setUserId(addFriendVo.getSerialNo());
        if(EnumSendType.FRIEND.getCode().equals(addFriendVo.getState())){
            friendShipsDo.setFriendId(addFriendVo.getUserId());
            friendShipsDo.setSendType(EnumSendType.FRIEND.getCode());
        }else{
            friendShipsDo.setGroupId(addFriendVo.getUserId());
            friendShipsDo.setSendType(EnumSendType.GROUP.getCode());
        }
        friendShipsDo.setStatus(EnumAddState.PASS.getCode());
        friendShipsDo.setCreateTime(new Date());
        friendShipsDo.setUpdateTime(new Date());
        friendShipsMapper.insertOrUpdate(friendShipsDo);
        return R.ok();
    }

    /**
     * 同意添加
     * @param addFriendVo
     * @return
     */
    @Override
    public R agreeFriend(AddFriendVo addFriendVo) {
        LambdaUpdateWrapper<FriendShipsDo> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(FriendShipsDo::getUserId, addFriendVo.getUserId())
                .eq(FriendShipsDo::getFriendId, addFriendVo.getSerialNo())
                .eq(FriendShipsDo::getSendType, addFriendVo.getState())
                .set(FriendShipsDo::getStatus, EnumAddState.SUCCESS.getCode())
                .set(FriendShipsDo::getUpdateTime, new Date());
        friendShipsMapper.update(queryWrapper);
        return R.ok();
    }

    /**
     * 查找添加我的请求
     * @param serialNo
     * @return
     */
    @Override
    public R<List<AddFriendDto>> searchAdd(String serialNo) {
        LambdaQueryWrapper<FriendShipsDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FriendShipsDo::getFriendId, serialNo)
                .orderByAsc(FriendShipsDo::getCreateTime);
        List<FriendShipsDo> friendShipsDos = friendShipsMapper.selectList(queryWrapper);
        List<AddFriendDto> addFriendDtos = new ArrayList<>();
        for (FriendShipsDo friendShipsDo : friendShipsDos) {
            UserInfoDo userInfoDo = userInfoMapper.selectById(friendShipsDo.getUserId());
            AddFriendDto addFriendDto = new AddFriendDto();
            addFriendDto.setSerialNo(userInfoDo.getSerialNo());
            addFriendDto.setAccount(userInfoDo.getAccount());
            addFriendDto.setUserName(userInfoDo.getUserName());
            addFriendDto.setPhone(userInfoDo.getPhone());
            addFriendDto.setPicture(userInfoDo.getPicture());
            addFriendDto.setState(friendShipsDo.getStatus());
            addFriendDtos.add(addFriendDto);
        }
        return R.ok(addFriendDtos);
    }
}
