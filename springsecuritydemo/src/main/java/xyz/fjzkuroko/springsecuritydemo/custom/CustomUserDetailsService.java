package xyz.fjzkuroko.springsecuritydemo.custom;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.fjzkuroko.springsecuritydemo.dao.UsersDAO;
import xyz.fjzkuroko.springsecuritydemo.entity.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/2/19 23:27
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名从数据库查询用户
        Users users = usersDAO.selectOne(new QueryWrapper<Users>().eq("username", username));
        // 判空
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //声明一个用于存放用户权限的列表
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 把该用户的角色添加到列表中
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + users.getRole()));
        // 查询数据库返回对象，获得用户名和密码
        return new User(users.getUsername(), /*new BCryptPasswordEncoder().encode(users.getPassword())*/users.getPassword(), grantedAuthorities);
    }
}
