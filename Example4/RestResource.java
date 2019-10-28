package spring_boot.spring_boot.Security.Example4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestResource {

    @GetMapping("/api/bicycle")
    public List<Bicycle2> get()
    {

        List<Bicycle2> bicycle2List = new ArrayList<>();
        bicycle2List.add(new Bicycle2("name","mark","model",56,78,32,"12.07.2008","DEXSC"));

        return bicycle2List;
    }
}
