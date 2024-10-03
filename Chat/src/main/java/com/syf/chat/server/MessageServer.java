package com.syf.chat.server;

import com.syf.chat.entity.dto.MessageInfoDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.MessageInfoDo;
import com.syf.chat.entity.vo.ClickFriendVo;

import java.util.List;

public interface MessageServer {
    /**
     * 点击用户
     * @param clickFriendVo 用户信息
     * @return              历史记录
     */
    R<List<MessageInfoDto>> click(ClickFriendVo clickFriendVo);

    /**
     * 发送消息
     * @param messageInfoDo 消息
     * @return        bool
     */
    R sendMessage(MessageInfoDo messageInfoDo);
}
