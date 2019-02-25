package com.example.tacocloud.security;

import com.example.tacocloud.service.UserRepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users " +
                                "where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities " +
                                "where username = ?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }*/

    /*
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("buzz")
                .password("infinity")
                .authorities("USER")
                .and()
                .withUser("woody")
                .password("bullseye")
                .authorities("ROLE_USER");
    }*/
}
