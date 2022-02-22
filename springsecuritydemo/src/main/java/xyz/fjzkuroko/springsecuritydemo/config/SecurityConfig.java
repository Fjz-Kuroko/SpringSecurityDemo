package xyz.fjzkuroko.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomAccessDeniedHandler;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomAuthenticationEntryPoint;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomAuthenticationFailHandler;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomAuthenticationSuccessHandler;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomLogoutSuccessHandler;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomPasswordEncoder;
import xyz.fjzkuroko.springsecuritydemo.custom.CustomUserDetailsService;

/**
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2022/2/19 23:15
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启基于方法的安全认证机制
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new CustomPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                /*匿名请求：不须要进行登陆拦截的url*/
                .authorizeRequests()
                .antMatchers("/test/**", "**/b").permitAll() //容许任何人访问
                .antMatchers("/test/admin").hasRole("admin")//当用户的角色是为admin时能够访问这个目录
                .anyRequest().authenticated()//其余的路径都是登陆后才可访问
                .and()
                /*登陆配置*/
                .formLogin()
                .successHandler(customAuthenticationSuccessHandler)//登陆成功处理
                .failureHandler(customAuthenticationFailHandler)//登陆失败处理
                .loginProcessingUrl("/login")//前端登陆请求地址
                .usernameParameter("username")//默认的用户名参数
                .passwordParameter("password")//默认的密码参数
                .permitAll()
                .and()
                /*登出配置*/
                .logout()
                .permitAll()
                .logoutSuccessHandler(customLogoutSuccessHandler) //退出处理
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)  //无权限时的处理
                .and()
                .cors() //跨域
                .and()
                //关闭csrf防御，相似于防火墙，不关闭上面的设置不会真正生效。
                .csrf().disable();
    }

    //密码加密配置
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
