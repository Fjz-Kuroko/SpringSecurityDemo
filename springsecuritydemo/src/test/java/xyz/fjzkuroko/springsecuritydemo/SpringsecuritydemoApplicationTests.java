package xyz.fjzkuroko.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.fjzkuroko.springsecuritydemo.utils.MD5Util;

@SpringBootTest
class SpringsecuritydemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5Util.getMD5("admin", MD5Util.LENGTH_32));
    }

}
