package com.syf.chat.server;

import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoUpdateServer {
    /**
     * 文件上传
     * @param file 文件信息
     */
    R fileUpload(MultipartFile file);

    /**
     * 更新用户信息
     */
    R<UserInfoDo> updateUserInfo(UserInfoDo userInfoDo);
}
