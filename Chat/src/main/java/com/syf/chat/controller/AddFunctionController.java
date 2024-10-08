package com.syf.chat.controller;

import com.syf.chat.entity.dto.AddFriendDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.vo.AddFriendVo;
import com.syf.chat.server.AddFriendServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class AddFunctionController {
    @Resource
    private AddFriendServer addFriendServer;

    /**
     * 搜索好友
     * @param addFriendVo
     * @return
     */
    @PostMapping("/search/friend")
    public R<List<AddFriendDto>> searchFriend(@RequestBody AddFriendVo addFriendVo) {
        return addFriendServer.searchFriend(addFriendVo);
    }

    /**
     * 添加好友
     * @param addFriendVo
     * @return
     */
    @PostMapping("/add/friend")
    public R addFriend(@RequestBody AddFriendVo addFriendVo) {
        return addFriendServer.addFriend(addFriendVo);
    }

    /**
     * 同意添加
     * @param addFriendVo
     * @return
     */
    @PostMapping("/agree/friend")
    public R agreeFriend(@RequestBody AddFriendVo addFriendVo) {
        return addFriendServer.agreeFriend(addFriendVo);
    }

    /**
     * 查找添加我的请求
     * @param map
     * @return
     */
    @PostMapping("/search/add")
    public R<List<AddFriendDto>> searchAdd(@RequestBody Map<String, String>map) {
        return addFriendServer.searchAdd(map.get("serialNo"));
    }
}
