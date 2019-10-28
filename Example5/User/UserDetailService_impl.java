package spring_boot.spring_boot.Security.Example5.User;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableResourceServer
@Configuration
public class UserDetailService_impl {

    private CustomUserDetailsServiceCar userDetailsServiceCar;
    private UserDetailsService userDetailsService;

    UserDetailsService userDetailsService()
    {
        UserDetails user = User.withDefaultPasswordEncoder().username("user")
                .password("user").roles("user")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    UserDetailsServiceAutoConfiguration userDetailServiceconfig(String model,Long id)
    {
        User
                .withUserDetails(userDetailsServiceCar.loadUserByModel(model))
                .username("model").password(passwordEncoder().encode("model")).roles("admin")
                .build();

        User
                .withUserDetails(userDetailsServiceCar.LoadUserById(id))
                .username("id").password(passwordEncoder().encode("id")).roles("admin")
                .build();



        return userDetailServiceconfig(model,id);
    }


    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
