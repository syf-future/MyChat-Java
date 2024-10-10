package com.syf.chat.controller;

import com.syf.chat.entity.dto.MessageInfoDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.MessageInfoDo;
import com.syf.chat.entity.vo.ClickFriendVo;
import com.syf.chat.server.MessageServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat/message")
public class MessageController {
    @Resource
    private MessageServer messageServer;
    /**
     * 点击好友
     * @return
     */
    @PostMapping("/click")
    public R<List<MessageInfoDto>> clickFriend(@RequestBody ClickFriendVo clickFriendVo) {
        return messageServer.click(clickFriendVo);
    }

    /**
     * 发送消息
     */
    @PostMapping("/send")
    public R sendMessage(@RequestBody MessageInfoDo messageInfoDo) {
        return messageServer.sendMessage(messageInfoDo);
    }
}
