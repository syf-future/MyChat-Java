package com.syf.chat.server;

import com.syf.chat.entity.dto.AddFriendDto;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.vo.AddFriendVo;

import java.util.List;

public interface AddFriendServer {
    /**
     * 搜索好友
     * @param addFriendVo
     * @return
     */
    R<List<AddFriendDto>> searchFriend(AddFriendVo addFriendVo);
    /**
     * 添加好友
     * @param addFriendVo
     * @return
     */
    R addFriend(AddFriendVo addFriendVo);

    /**
     * 同意添加
     * @param addFriendVo
     * @return
     */
    R agreeFriend(AddFriendVo addFriendVo);

    /**
     * 查找添加我的请求
     * @param serialNo
     * @return
     */
    R<List<AddFriendDto>> searchAdd(String serialNo);
}
