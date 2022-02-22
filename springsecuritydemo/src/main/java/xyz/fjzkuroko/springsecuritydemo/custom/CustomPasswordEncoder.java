package xyz.fjzkuroko.springsecuritydemo.custom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.fjzkuroko.springsecuritydemo.utils.MD5Util;

/**
 * 自定义密码加密类
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/2/20 11:23
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.getMD5(rawPassword.toString(), MD5Util.LENGTH_32);
    }

    /**
     *
     * @param rawPassword 前端传递的明文密码
     * @param encodedPassword 数据库中的加密密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.getMD5(rawPassword.toString(), MD5Util.LENGTH_32));
    }
}
