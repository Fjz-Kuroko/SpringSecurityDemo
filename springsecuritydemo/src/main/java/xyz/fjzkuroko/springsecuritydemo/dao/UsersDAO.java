package xyz.fjzkuroko.springsecuritydemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import xyz.fjzkuroko.springsecuritydemo.entity.Users;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/2/19 23:43
 */
@Repository
public interface UsersDAO extends BaseMapper<Users> {
}
