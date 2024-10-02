package com.syf.chat.server.handler;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.syf.chat.common.enums.EnumMessageType;
import com.syf.chat.common.enums.EnumSendType;
import com.syf.chat.entity.model.MessageInfoDo;
import com.syf.chat.entity.vo.ClickFriendVo;
import com.syf.chat.mapper.MessageInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息处理类
 */
@Component
public class MessageHandler {
    @Resource
    private MessageInfoMapper messageInfoMapper;

    /**
     * 获取文本聊天记录
     * @param clickFriendVo 用户信息
     * @return
     */
    public List<MessageInfoDo> getTextMessage(ClickFriendVo clickFriendVo) {
        LambdaQueryWrapper<MessageInfoDo> messageWrapper = new LambdaQueryWrapper<>();
        messageWrapper.eq(MessageInfoDo::getSendType, EnumSendType.FRIEND.getCode())
                .eq(MessageInfoDo::getMessageType, EnumMessageType.TEXT.getCode())
                .and(wrappers -> wrappers
                        // 我给好友发消息
                        .eq(MessageInfoDo::getUserId, clickFriendVo.getUserId())
                        .eq(MessageInfoDo::getFriendId, clickFriendVo.getFriendId())
                        .or()
                        //好友给我发消息
                        .eq(MessageInfoDo::getUserId, clickFriendVo.getFriendId())
                        .eq(MessageInfoDo::getFriendId, clickFriendVo.getUserId())
                ).orderByDesc(MessageInfoDo::getCreateTime); // 按照创建时间降序排列
        return messageInfoMapper.selectList(messageWrapper);
    }
    /**
     * 发送消息
     * @param messageInfoDo
     */
    public void saveMessage(MessageInfoDo messageInfoDo) {
        messageInfoMapper.insert(messageInfoDo);
    }
}
