package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_H2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public  class SecurityConfigH2Bicycle extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication().withUser("USER").password(passwordEncoder().encode("USER")).roles("USER")
                .and().withUser("ADMIN").password(passwordEncoder().encode("ADMIN")).roles("USER","ADMIN");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/getBicycle").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/addBicycle").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/updateBicycle").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH,"/patchBicycle").hasRole("ADMIN")
                .antMatchers("/database").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/deleteBicycle").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
        http.headers().frameOptions().disable()
                .and().csrf().disable();



    }

}