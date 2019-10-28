package spring_boot.spring_boot.Security.Example5.User;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;


public class UserBuilder_ {

private UserBuilder build;
private Function<String,String> encoder;

public UserBuilder_(UserBuilder builder)
{

    this.build = builder;

    UserDetails details = builder.build();
    UserBuilder name = builder.username("Admin");
    UserBuilder passw = builder.password("#$%^12");
    builder.roles("Admin");
    builder.authorities("AUTH");
    builder.passwordEncoder(encoder);
    builder.accountExpired(true);
    builder.accountLocked(false);

    if(details.getPassword() != null)
    {
        passw.roles("Admin").disabled(false);
    }
    else
    {
        details.getPassword().equals(null);
        passw.roles("EMPTY").disabled(true);
        builder.disabled(true);
    }

    if(name.equals(null))
    {
        name.disabled(true);
        name.roles("DISABLE_ROLE").accountLocked(true);
    }



}


}
