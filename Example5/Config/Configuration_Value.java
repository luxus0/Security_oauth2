package spring_boot.spring_boot.Security.Example5.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import spring_boot.spring_boot.Security.Example5.Entity.Car;
import spring_boot.spring_boot.Security.Example5.Repo.CarRepo;

@Service
public class Configuration_Value
{
    private CarRepo repo;

    @Autowired
    public Configuration_Value(CarRepo repo) {
        this.repo = repo;
    }

    @Value("${name}")
    private String name;

    @Value("${mark}")
    private String mark;

    @Value("${model}")
    private String model;




    @EventListener(ApplicationReadyEvent.class)
    public void addCar()
    {
        Car car = new Car(1,mark,model,23);
        Car car1 = new Car(2,mark,model,17);
        Car car2 = new Car(3,mark,model,33);
        Car car3 = new Car(4,mark,model,45);


        repo.save(car);
        repo.save(car1);
        repo.save(car2);
        repo.save(car3);



    }

}
