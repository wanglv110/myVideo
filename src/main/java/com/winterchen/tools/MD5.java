package com.winterchen.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * md5 加密解密算法
 *
 * @Title: md5 加密解密算法
 * @Description: 提供md5加密的算法
 * @Author: wjk
 * @Date: 2019/12/10
 */
public class MD5 {

    /**
     * 将字符串以md5方式加密的方法
     * @param s 需要加密的字符串
     * @return 以md5加密后的值
     */
    public static String getMD5String(String s) {
        // 声明字符数组
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        getMD5String("123");
    }
}
