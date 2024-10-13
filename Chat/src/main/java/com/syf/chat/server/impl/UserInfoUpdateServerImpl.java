package com.syf.chat.server.impl;

import com.syf.chat.common.constants.FileConstant;
import com.syf.chat.common.utils.DateUtil;
import com.syf.chat.common.utils.UUIdUtil;
import com.syf.chat.entity.dto.R;
import com.syf.chat.entity.model.UserInfoDo;
import com.syf.chat.mapper.UserInfoMapper;
import com.syf.chat.server.UserInfoUpdateServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserInfoUpdateServerImpl implements UserInfoUpdateServer {
    @Resource
    private UserInfoMapper userInfoMapper;

    // 上传url
    @Value("${upload.url}")
    private String uploadUrl;
    // 头像请求url
    @Value("${upload.file.image.profile.address}")
    private String profileUrl;
    // 上传头像路径
    @Value("${upload.file.image.profile.path}")
    private String profilePath;


    /**
     * 文件上传
     *
     * @param file 文件信息
     */
    @Override
    public R fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return R.fail();
        }
        //文件名
        String fileName = UUIdUtil.getUUId() + "." + FileConstant.JPG;
        File dest = new File(profilePath + fileName);
        //创建目录
        if (!dest.exists()) {
            dest.mkdirs();
        }
        try {
            file.transferTo(dest);
            // 返回文件的访问路径  服务器地址+文件名
            String fileUrl = uploadUrl + profileUrl + fileName;
            Map<String, String> map = new HashMap<>();
            map.put("imageUrl", fileUrl);
            return R.ok(map);
        } catch (IOException e) {
            log.error("文件上传失败", e);
            return R.fail();
        }
    }

    /**
     * 更新用户信息
     */
    @Override
    public R<UserInfoDo> updateUserInfo(UserInfoDo userInfoDo) {
        userInfoDo.setUpdateTime(DateUtil.getDate());
        userInfoMapper.updateById(userInfoDo);
        return R.ok(userInfoDo);
    }
}
