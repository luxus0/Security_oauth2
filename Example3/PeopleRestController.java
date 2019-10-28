package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PeopleRestController {


    @GetMapping("/get")
    public Optional<People> getPeople(@RequestBody People people)
    {
        List<People> peopleList = new ArrayList<>();


       peopleList.add(new People(people.getId(),people.getName(),people.getSurname(),people.getHeight(),people.getWage(),people.getAge(),people.getEmail()));

       return peopleList.stream().findAny();

    }

    @GetMapping("/get/{id}")
    public People getPeopleId(@RequestBody People people, @PathVariable("id") Integer id)
    {

        people.setId(id);
        List<People> peopleList = new ArrayList<>();
        return peopleList.get(id);

    }

}
