package com.example.lab4.configuration;

import com.example.lab4.tools.CheckArea;
import com.example.lab4.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    @Qualifier("userDetailsServ")
    private UserDetailsService userDetailsService;

//    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//            "classpath:/META-INF/resources/", "classpath:/resources/",
//            "classpath:/static/", "classpath:/public/" };
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
    @Bean
    public CheckArea checkArea(){return new CheckArea(); }

    @Bean
    public User logUser(){
        return  new User();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        super.configure(webSecurity);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/register","/api/register","/api/login","/index*", "/static/**", "/*.js", "/*.json", "/*.ico", "/login", "/logout","/getpoints","/checkpoints").permitAll()
                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin()
//               .loginPage("/login")
//                .loginPage("/register")
                .and()
                .csrf()
                .disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .permitAll()
                .and()
                .httpBasic();
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED); //А что это?
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("localhost:4200/Lab4"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


}
