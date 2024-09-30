package com.syf.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syf.chat.entity.model.UserInfoDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDo> {
}
