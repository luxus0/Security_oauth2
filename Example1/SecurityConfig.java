package lukasz.nowogorski.spring_security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.inMemoryAuthentication().withUser("Admin").
                password(passwordEncoder().encode("admin000"))
                .roles("ADMIN","USER")
                    .and().
                    withUser("USER").
                password(passwordEncoder().encode("USER"))
                .roles("USER");
    }

    protected void configure(HttpSecurity security) throws Exception
    {
        security.authorizeRequests().antMatchers("/hello").hasRole("ADMIN").antMatchers("/hi")
                .authenticated().and().
                formLogin().permitAll().and().logout().permitAll();

        security.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/helloAdmin").hasRole("ADMIN")
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();

            security.headers().frameOptions().disable();

    }

}
