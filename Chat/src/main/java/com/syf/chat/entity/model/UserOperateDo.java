package com.syf.chat.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户操作表
 */
@Data
@TableName("USER_OPERATE")
public class UserOperateDo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 流水号
     */
    @TableId
    private String serialNo;
    /**
     * 登录状态（LOGIN：登录，UNLOGIN：未登录）
     */
    private String loginState;
    /**
     * 操作状态(0 未操作  friendId)
     */
    private String operateState;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
