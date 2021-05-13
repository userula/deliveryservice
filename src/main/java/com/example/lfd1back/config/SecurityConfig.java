package com.example.lfd1back.config;

import com.example.lfd1back.model.User;
import com.example.lfd1back.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public SecurityConfig(@Qualifier("myUserDetailsService") UserDetailsService userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

    @Override
    public void configure(HttpSecurity security) throws Exception{
        security.csrf().disable().authorizeRequests()
                .antMatchers().hasAnyRole() //ex
//                .anyRequest().hasAnyRole()

                .and()
                .formLogin()
                .loginPage("/login") //login page url
                .usernameParameter("email") //email
                .passwordParameter("password") //password
                .successHandler(authenticationSuccessHandler()) //method to success authentication
                .failureHandler(authenticationFailureHandler()) //method to failure authentication

                .and()
                .logout() //logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) //url of logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/") //go to welcome page
                .permitAll();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler(){
        return ((httpServletRequest, httpServletResponse, authentication) -> {
            //get user from DB
            String email = httpServletRequest.getParameter("email");
            String password = httpServletRequest.getParameter("password");
            User user = userRepository.findByEmail(email);

            HttpSession session = httpServletRequest.getSession();
            session.setMaxInactiveInterval(60 * 10); //5 minutes
            session.setAttribute("user", user);

            user.getAuthorities().forEach(System.out::println);

            httpServletResponse.sendRedirect("/"); //go to main page
        });
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return ((request, response, authentication) -> {
            response.sendRedirect("/?error"); //go to back with error attribute
        });
    }

}
