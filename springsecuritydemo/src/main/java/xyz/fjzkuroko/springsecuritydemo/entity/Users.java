package xyz.fjzkuroko.springsecuritydemo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/2/19 23:40
 */
@Data
@ToString
public class Users {

    private Long id;
    private String username;
    private String password;
    private String role;

}
