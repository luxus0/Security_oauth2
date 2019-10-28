package spring_boot.spring_boot.Security.Example5.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig implements ResourceServerConfigurer {


    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {

        resourceServerSecurityConfigurer
                .resourceId("resource_id")
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .anonymous()
                .disable()
                .authorizeRequests()
                .antMatchers("/users").access("role('admin')")
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
