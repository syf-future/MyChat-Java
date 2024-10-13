package com.syf.chat.common.utils;

import java.util.Base64;
import java.util.UUID;

public class UUIdUtil {
    public static String getUUId() {
        UUID uuid = UUID.randomUUID();

        // 获取 UUID 的字节数组
        byte[] uuidBytes = asBytes(uuid);

        // 使用 Base64 编码生成短 UUID
        return Base64.getUrlEncoder().withoutPadding().encodeToString(uuidBytes);
    }

    // 将 UUID 转为字节数组
    private static byte[] asBytes(UUID uuid) {
        byte[] bytes = new byte[16];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (msb >>> (8 * (7 - i)));
            bytes[8 + i] = (byte) (lsb >>> (8 * (7 - i)));
        }
        return bytes;
    }
}
