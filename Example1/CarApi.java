package lukasz.nowogorski.spring_security;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarApi
{
    private List<Car> carList;

    public CarApi()
    {
        carList = new ArrayList<>();
        carList.add(new Car(1,"FIat","Zafira"));
        carList.add(new Car(2,"Seat","Ibiza"));
    }

    @GetMapping("/car")
    public List<Car> getCar()
    {
        return carList;
    }

    @GetMapping("/car/{mark}")
    public Car getCarID(@RequestParam String mark)
    {
        return carList.stream().filter(p -> p.getMark() == mark).findFirst().get();
    }

    @PostMapping("/car")
    public boolean addCar(@RequestBody Car car)
    {
        return carList.add(car);
    }

    @DeleteMapping("/car")
    public boolean deleteCar(@RequestParam long id)
    {
        Optional<Car> first = carList.stream().filter(p -> p.getId() == id).findFirst();

        if(first.isPresent())
        {
            return carList.remove(first.get());
        }
        return false;
    }
}
