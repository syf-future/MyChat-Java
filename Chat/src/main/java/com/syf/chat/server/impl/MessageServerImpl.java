package com.syf.chat.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.syf.chat.common.enums.EnumLoginState;
import com.syf.chat.common.enums.EnumSendType;
import com.syf.chat.common.utils.SequenceTool;
import com.syf.chat.entity.dto.MessageInfoDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.MessageInfoDo;
import com.syf.chat.entity.model.UserOperateDo;
import com.syf.chat.entity.vo.ClickFriendVo;
import com.syf.chat.mapper.MessageInfoMapper;
import com.syf.chat.mapper.UserOperateMapper;
import com.syf.chat.server.MessageServer;
import com.syf.chat.server.handler.MessageHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MessageServerImpl implements MessageServer {
    @Resource
    private MessageHandler messageHandler;
    @Resource
    private MessageInfoMapper messageInfoMapper;
    @Resource
    private UserOperateMapper userOperateMapper;
    /**
     * 点击用户
     * @param clickFriendVo 用户信息
     * @return              历史记录
     */
    @Override
    public R<List<MessageInfoDto>> click(ClickFriendVo clickFriendVo) {
        //更改操作状态
        UserOperateDo userOperateDo = new UserOperateDo();
        userOperateDo.setSerialNo(clickFriendVo.getUserId());
        userOperateDo.setOperateState(clickFriendVo.getFriendId());
        userOperateDo.setUpdateTime(new Date());
        userOperateMapper.updateById(userOperateDo);

        //更新消息状态为已读
        LambdaUpdateWrapper<MessageInfoDo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(MessageInfoDo::getUserId,clickFriendVo.getFriendId())
                .eq(MessageInfoDo::getFriendId, clickFriendVo.getUserId() )
                .eq(MessageInfoDo::getIsRead, "0")
                .set(MessageInfoDo::getIsRead, "1");
        messageInfoMapper.update(updateWrapper);
        //获取历史消息
        List<MessageInfoDo> textMessage = messageHandler.getTextMessage(clickFriendVo);
        List<MessageInfoDto> messageInfoDtoList = new ArrayList<>();
        for (MessageInfoDo messageInfoDo : textMessage) {
            MessageInfoDto messageInfoDto = new MessageInfoDto();
            messageInfoDto.setUserId(messageInfoDo.getUserId());
            messageInfoDto.setMessageType(messageInfoDo.getMessageType());
            messageInfoDto.setContent(messageInfoDo.getContent());
            messageInfoDto.setSendDate(messageInfoDo.getUpdateTime());
            messageInfoDtoList.add(messageInfoDto);
        }
        return R.ok(messageInfoDtoList);
    }

    /**
     * 发送消息
     * @param messageInfoDo 消息
     * @return        bool
     */
    @Override
    public R sendMessage(MessageInfoDo messageInfoDo) {
        Long state = 0L;
        if(EnumSendType.FRIEND.getCode().equals(messageInfoDo.getSendType())){
            LambdaQueryWrapper<UserOperateDo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserOperateDo::getSerialNo, messageInfoDo.getFriendId())
                    .eq(UserOperateDo::getLoginState, EnumLoginState.LOGIN.getCode())
                    .eq(UserOperateDo::getOperateState, messageInfoDo.getUserId());
            state = userOperateMapper.selectCount(queryWrapper);
        }
        messageInfoDo.setSerialNo(SequenceTool.nextId());
        messageInfoDo.setCreateTime(new Date());
        messageInfoDo.setUpdateTime(new Date());
        messageInfoDo.setIsRead(String.valueOf(state));
        messageInfoMapper.insert(messageInfoDo);
        return R.ok();
    }
}
