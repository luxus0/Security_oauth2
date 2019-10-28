package spring_boot.spring_boot.Security.Example5.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.HttpStatusReturningServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Locale;

@Component
public class CustomLogoutSuccessHandler extends HttpStatusReturningServerLogoutSuccessHandler implements LogoutSuccessHandler, LogoutHandler {




    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        long DateHeader = request.getDateHeader("date_header");
        request.setAttribute("Attribute1","attrib");
        String token = request.getHeader("Header authorization");

        if(token != null && token.startsWith("Header"))
        {
            OAuth2AccessToken oAuth2AccessToken = tokenStore().readAccessToken(String.valueOf(token.split("Header")));

            if(oAuth2AccessToken != null)
            {
                tokenStore().removeAccessToken(oAuth2AccessToken);
            }
        }

        Cookie cookie = new Cookie("Cookie 1","COOKIE_VALUE");
        response.setStatus(HttpServletResponse.SC_OK);
        response.addCookie(cookie);
        response.setBufferSize(12);

        response.setLocale(Locale.ENGLISH);
        authentication.setAuthenticated(true);

    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        if(httpServletResponse.getStatus() == 0) {

            httpServletRequest.logout();
        }
        else {
            httpServletResponse.encodeRedirectURL("http://localhost:8090/login");
        }
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
