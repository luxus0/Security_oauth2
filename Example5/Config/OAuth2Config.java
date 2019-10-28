package spring_boot.spring_boot.Security.Example5.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {


    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private TokenStore tokenStore;





    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userDetailsService)
                .tokenStore(getTokenStore());
    }


    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/security").hasRole("admin")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");


    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("User1")
                .secret("secret")
                .authorities("role__client")
                .authorizedGrantTypes("authorization_code", "refresh_code")
                .scopes("read,write")
                .autoApprove(true)

                .and()
                    .withClient("User2")
                    .secret("secret2")
                    .authorities("role_client2")
                    .authorizedGrantTypes("authorization_code","refresh_code")
                    .scopes("read1,write2")
                    .autoApprove(true)

            .and()
                    .withClient("User3")
                    .secret("secret3")
                    .authorities("role_client3")
                    .scopes("read2,write2")
                    .authorizedGrantTypes("authorization_code","refresh_code")
                    .autoApprove(true);

    }

    @Bean
public TokenStore getTokenStore()
{
    return this.tokenStore;
}



}

