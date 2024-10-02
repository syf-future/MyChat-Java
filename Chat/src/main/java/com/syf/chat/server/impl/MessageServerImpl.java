package com.syf.chat.server.impl;

import com.syf.chat.common.utils.SequenceTool;
import com.syf.chat.entity.dto.MessageInfoDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.Message;
import com.syf.chat.entity.model.MessageInfoDo;
import com.syf.chat.entity.vo.ClickFriendVo;
import com.syf.chat.mapper.MessageInfoMapper;
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
    /**
     * 点击用户
     * @param clickFriendVo 用户信息
     * @return              历史记录
     */
    @Override
    public R<List<MessageInfoDto>> click(ClickFriendVo clickFriendVo) {
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
     * @param message 消息
     * @return        bool
     */
    @Override
    public R sendMessage(Message message) {
        MessageInfoDo messageInfoDo = message.getData();
        messageInfoDo.setSerialNo(SequenceTool.nextId());
        messageInfoDo.setCreateTime(new Date());
        messageInfoDo.setUpdateTime(new Date());
        messageInfoMapper.insert(messageInfoDo);
        return R.ok();
    }
}
