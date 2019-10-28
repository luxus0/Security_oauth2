package spring_boot.spring_boot.Security.Example5.Provider;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import spring_boot.spring_boot.Security.Example5.User.CurrentUser;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AuthenticationProvider_example<CustomUserDetails> implements AuthenticationProvider {

    private User.UserBuilder userBuilder;
    private Principal principal;
    private AuthenticationProvider provider;
    private UserDetails userDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = "Andaleko";
        String pass = "Iriatol";
        Object credential = authentication.getCredentials();


        User.UserBuilder n = userBuilder.username("Marikak");
        User.UserBuilder p = userBuilder.password("Pirotel");

        Map<User.UserBuilder,String> authMap = new HashMap<>();
        authMap.put(n,pass);
        authMap.put(p,name);

        for(User.UserBuilder buildAuth : authMap.keySet() )
        {
            buildAuth.roles("AUTH_ROLES").disabled(false).password(authMap.get(pass));
            authentication.setAuthenticated(true);
            buildAuth.credentialsExpired(true);


        }
        
        if(credential == null) 
        {
            AuthenticationException ex = null;
            System.out.println(ex.getMessage());
        }
        else
        {
            Object det = authentication.getDetails();
            userBuilder.roles((String) credential);
            if(userBuilder.equals(det))
            {
                System.out.println( " Principal name is empty?" +principal.getName().isEmpty());
            }
        }

        String userId = authMap.get(0);
        if(StringUtils.isEmpty(userId))
        {
            String error = authMap.get(1);
            throw new BadCredentialsException(error);
        }

        return authentication;



        }



    @Override
    public boolean supports(Class<?> aClass) {

        AuthenticationProvider_example provider_example = new AuthenticationProvider_example();

        Logger log = (Logger) LoggerFactory.getLogger(provider_example.getClass());
        Logger log2 = (Logger) LoggerFactory.getLogger(CurrentUser.class);

        if(provider.supports(CurrentUser.class) && provider.supports(provider_example.getClass()))
        {
            log.info("ALL CLASS IS PROVIDER");
        }

        return true;
    }


    private Map<String, String> getUserServicePostObject(String username, String password)
    {
        Map<String, String> requestParam = new HashMap<String, String>();
        requestParam.put("username",username);
        requestParam.put("password",password);

        return requestParam;
    }

    private UserDetails buildCustomUserDetails(String username, String password, String userId)
    {
        if(userDetails.getUsername().equals(userDetails.getPassword() ))
        {
            Assert.notNull(username,password);
        }

        return userDetails;
    }
}
