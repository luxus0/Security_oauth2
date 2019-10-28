package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AddPeopleDB {


    @EventListener(ApplicationReadyEvent.class)
    public void addDB()
    {
        People people = new People();
        People people2 = new People();
        People people3 = new People();
        People people4 = new People();
        People people5 = new People();


        people.setId(1);
        people.setName("Marko");
        people.setSurname("Miresta");
        people.setHeight(180);
        people.setWage(80);
        people.setAge(23);
        people.setEmail("loako@o2s.drf");

        people2.setId(2);
        people2.setName("Zirat");
        people2.setSurname("Fatko");
        people2.setHeight(190);
        people2.setWage(85);
        people2.setAge(24);
        people2.setEmail("sdfd@o2s.drf");

        people3.setId(3);
        people3.setName("Mnematkia");
        people3.setSurname("Zafira");
        people3.setHeight(175);
        people3.setWage(78);
        people3.setAge(29);
        people3.setEmail("edlas@o2s.drf");

        people4.setId(4);
        people4.setName("dsdtkia");
        people4.setSurname("Zdffa");
        people4.setHeight(675);
        people4.setWage(178);
        people4.setAge(329);
        people4.setEmail("detakse@o2s.drf");

        people5.setId(5);
        people5.setName("Batera");
        people5.setSurname("Sofoan");
        people5.setHeight(12);
        people5.setWage(165);
        people5.setAge(34);
        people5.setEmail("addds.drf");



    }





}
