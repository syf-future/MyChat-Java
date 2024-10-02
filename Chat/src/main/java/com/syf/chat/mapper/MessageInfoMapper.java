package com.syf.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syf.chat.entity.model.MessageInfoDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageInfoMapper extends BaseMapper<MessageInfoDo> {
}
