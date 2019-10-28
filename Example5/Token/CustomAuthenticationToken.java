package spring_boot.spring_boot.Security.Example5.Token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import javax.security.auth.Subject;
import java.util.Map;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private String Credetial;
    private String principal;
    private  Subject subject;


    public CustomAuthenticationToken(Map<String,String> authorities) {
        super(null);
        authorities.put(Credetial,principal);
        subject.getPrincipals(CustomAuthenticationToken.class);
        subject.getPrivateCredentials(CustomAuthenticationToken.class);
    }

    public void getReadOnly()
    {
        if(subject.isReadOnly())
        {
            subject.setReadOnly();
            System.out.println("Subject is read only");
        }
    }

    @Override
    public Object getCredentials() {
        return "CREDENTIAL";
    }

    public void setCredential(String credential) {
        this.Credetial = credential;
    }

    @Override
    public Object getPrincipal() {
        return "PRINCIPAL";
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return this.subject;
    }

    @Override
    public boolean implies(Subject subject) {
        return true;
    }


}
