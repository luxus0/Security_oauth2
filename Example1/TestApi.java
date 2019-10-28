package lukasz.nowogorski.spring_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi
{
    @GetMapping("/hello")
    public String hello()
    {
        return "Hello";
    }

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

    @GetMapping("/forAll")
    public String forAll() {
        return "forAll";
    }
}
