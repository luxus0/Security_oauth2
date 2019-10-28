package spring_boot.spring_boot.Security.Example5.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.EncodingException;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2ClientConfiguration;

import javax.management.AttributeNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore.Entry.Attribute;
import java.util.Enumeration;
import java.util.Locale;

@Configuration
public class Client_Config extends OAuth2ClientConfiguration {

    private OAuth2ClientContextFilter filter;
    private OAuth2ClientConfiguration configure;
    private ServletRequest servletRequest;
    private Attribute attribute;
    private ServletResponse servletResponse;
    private FilterChain filterChain;
    private FilterConfig filterConfig;

    public void configure(OAuth2ClientConfiguration configure,OAuth2ClientContextFilter filter) throws Exception
    {
        this.filter = filter;
        filter = configure.oauth2ClientContextFilter();
        filter.doFilter(getServletRequests(),getServletResponse(),getFilterChain());
        filterConfig.getInitParameter("Parametrize");
        filter.init(filterConfig);

    }

    public ServletRequest getServletRequests() throws Exception {
        servletRequest.setAttribute("ATTRIB1", attribute.getName().indexOf("33"));
        servletRequest.setCharacterEncoding("utf-16");
        String locale = servletRequest.getLocale().getDisplayName(Locale.CHINESE);
       if(HttpProperties.Encoding.Type.REQUEST.compareTo(HttpProperties.Encoding.Type.RESPONSE) == 0)
       {
           Exception e;
           e = new EncodingException("EXCEPTION TYPE ENCODING");
           Logger log = LoggerFactory.getLogger("TYPE ENCODING");
           log.info(e.getMessage(),e);
       }

       return servletRequest;
    }

    public ServletResponse getServletResponse() throws Exception
    {
        byte[] b = {2,3,4,5,6,7};

        servletResponse.setContentLength(20);
        servletResponse.setBufferSize(20);
        servletResponse.setCharacterEncoding("utf-16");
        servletResponse.setContentType("text/html");
        servletResponse.setLocale(Locale.CHINESE);
        servletResponse.getOutputStream().write(b,0,7);
        servletResponse.reset();
        servletResponse.resetBuffer();




        return servletResponse;
    }

    public FilterChain getFilterChain()
    {
        return filterChain;
    }
}
