package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class oAuth2SecurityConfig extends AuthorizationServerConfigurerAdapter {

@Autowired
    private DataSource dataSource;


    private AuthenticationManager authenticationManager;




    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
    {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients
                .inMemory()
                .withClient("FrontendClientId")
                .secret(passwordEncoder().encode("ADMIN"))
                .authorities("read_only")
                .resourceIds("oath2-resource")
                .redirectUris("http://locahost:8090/people")
                .authorizedGrantTypes("password","authorization_code","refresh_token")
                .accessTokenValiditySeconds(5000)
                .refreshTokenValiditySeconds(24*5*30)
                .scopes("read","write").autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService());

    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() { return new JdbcTokenStore(dataSource); }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("USER")
                .password("USER")
                .roles("Moderator")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("ADMIN")
                .password("ADMIN")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }




}
