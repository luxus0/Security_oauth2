package spring_boot.spring_boot.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements  AuthenticationEntryPoint {

    Logger getLogger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        boolean a = httpServletRequest.authenticate(httpServletResponse);

       getLogger.error("RESPONDING WITH BAD REQUEST",e.getLocalizedMessage());
       getLogger.error("RESPONDING WITH UNATHORIZE", e.getLocalizedMessage());

       if(HttpServletResponse.SC_BAD_REQUEST != 0) {
           httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
           httpServletResponse.setStatus(400);
       }

        if(HttpServletResponse.SC_UNAUTHORIZED != 0) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            httpServletResponse.setStatus(401);
        }
    }
}
