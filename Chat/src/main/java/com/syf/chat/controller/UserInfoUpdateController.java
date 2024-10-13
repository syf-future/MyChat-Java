package com.syf.chat.controller;

import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.server.UserInfoUpdateServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息变更
 */
@RestController
@RequestMapping("/chat/update")
public class UserInfoUpdateController {
    @Resource
    private UserInfoUpdateServer userInfoUpdateServer;

    /**
     * 文件上传
     * @param file 文件信息
     */
    @PostMapping("/upload")
    public R fileUpload(@RequestParam("file") MultipartFile file) {
        return userInfoUpdateServer.fileUpload(file);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/userInfo")
    public R<UserInfoDo> updateUserInfo(@RequestBody UserInfoDo userInfoDo) {
        return userInfoUpdateServer.updateUserInfo(userInfoDo);
    }
}
