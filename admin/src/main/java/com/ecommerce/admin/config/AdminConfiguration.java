package com.ecommerce.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class AdminConfiguration {


    @Bean
    public UserDetailsService userDetailsService(){
    return new AdminServiceConfig();
     }
     @Bean
    public BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
     }

     @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(userDetailsService());
      provider.setPasswordEncoder(passwordEncoder());
      return provider;

     }
       @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           http.csrf()
                   .disable()
                   .authorizeHttpRequests().antMatchers("/login").permitAll()
                   .and()
                   .formLogin()
                   .loginPage("/login")
                   .loginProcessingUrl("/do-login")
                   .defaultSuccessUrl("/admin/index")
                   .and()
                   .logout()
                   .invalidateHttpSession(true)
                   .clearAuthentication(true)
                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                   .logoutSuccessUrl("/login/logout");
           return http.build();

       }

}
