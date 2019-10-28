package spring_boot.spring_boot.Security.Example5.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring_boot.spring_boot.Security.Example5.Entity.Car;
import spring_boot.spring_boot.Security.Example5.Repo.CarRepo;


import javax.security.auth.Subject;
import java.security.AccessControlContext;
import java.security.DomainCombiner;
import java.security.ProtectionDomain;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CarRepo repo;

    private AccessControlContext access;
    private java.security.Permission permission;
    private String namePermission;
    private Object credential;
    private Object authorities;
    private Object details;
    private Object principal;
    private DomainCombiner domainCombiner;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("User")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("Admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/get").hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/get/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/post").hasRole("Admin")
                .antMatchers(HttpMethod.PUT, "/put/{id}").hasRole("Admin")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
        http.headers().frameOptions().disable();


    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        Car car = new Car(1, "Marker", "Medelloto", 23);
        Car car2 = new Car(2, "Marfsdfker", "Medsdfsfo", 25);
        Car car3 = new Car(3, "Madddrker", "Medelloto", 27);
        Car car4 = new Car(4, "Maddeedrker", "Medefsdlloto", 28);

        repo.save(car);
        repo.save(car2);
        repo.save(car3);
        repo.save(car4);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public void authenticationEventPublisher() {
        AuthenticationEventPublisher event = new AuthenticationEventPublisher() {

            SecurityConfig permissionConfig = new SecurityConfig();

            @Override
            public void publishAuthenticationSuccess(Authentication authentication) {
                permissionConfig = new SecurityConfig();
            authentication.setAuthenticated(true);
            permission.checkGuard("Guard");
            access.checkPermission(permission);
           permissionConfig.setNamePermission("PERMISSON_SUCCED");

            System.out.println( "Name permission: " +permissionConfig);

            }

            @Override
            public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
            authentication.setAuthenticated(true);


            authentication.implies(Subject.getSubject(access));
            permissionConfig.setAuthorities("auth1");
            permissionConfig.setCredential("cred1");
            permissionConfig.setDetails("AUTH_CREDENTIAL");

            permissionConfig.setAuthorities(authentication.getCredentials());

                Logger log = LoggerFactory.getLogger("AUTHORITIES");
            if(authentication.isAuthenticated())
            {

                log.info("PERMISSION SUCCESSFULL");
            }
            else
            {
                System.out.println("ERROR MESSAGE:" +e.getMessage());
                log.error("PERMISSION ERROR");
            }

            }
        };


    }

    public String getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(String namePermission) {
        this.namePermission = namePermission;
    }

    public Object getCredential() {
        return credential;
    }

    public void setCredential(Object credential) {
        this.credential = credential;
    }

    public Object getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Object authorities) {
        this.authorities = authorities;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }
}