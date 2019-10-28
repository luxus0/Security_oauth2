package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@PropertySource("classpath:Security_ConfigJdbc.properties")
@PropertySource("classpath:PeopleSecurityConfig.sql")
public class SecurityConfigJdbcPeople extends WebSecurityConfigurerAdapter
{


    @Value("${URL}")
    private String url;

    @Value("${USERNAME}")
    private String username;

    @Value("${PASSWORD}")
    private String password;


    public DataSource getDataSource()  {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }



    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                //.antMatchers(url).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/get").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();

    }





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.
               jdbcAuthentication()
               .dataSource(getDataSource())
               .usersByUsernameQuery("SELECT name,surname,age as principal, password as credentials, true FROM people WHERE name = ? ")
                .passwordEncoder(getPasswordEncoder())
                .authoritiesByUsernameQuery("SELECT weight,age,email as principal, authority as role FROM people WHERE id = ? ").and().inMemoryAuthentication()
                .withUser("ADMIN")
              .password(getPasswordEncoder().encode("ADMIN"))
                .roles("ADMIN");


    }



}
