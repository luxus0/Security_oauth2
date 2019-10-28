package spring_boot.spring_boot.Security.Example5.Api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot.spring_boot.Security.Example5.Entity.Car;
import spring_boot.spring_boot.Security.Example5.Repo.CarRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CarApi {

    private CarRepo repo;
    private List<Car> carList = new ArrayList<>();

    public CarApi()
    {
        carList.add(new Car(1,"markerwe","modeldsdlo",10));
        carList.add(new Car(2,"markerai","modsadsello",15));
        carList.add(new Car(3,"markeret","modasdello",34));
        carList.add(new Car(4,"markermu","modasdsello",20));
        carList.add(new Car(5,"markerfz","modsadello",34));
    }

    @GetMapping("/get")
    public List<Car> getCar()
    {
        return carList;
    }

    @GetMapping("/get/{id}")
    public Car getCarId(@PathVariable("id") Integer id)
    {
        return carList.get(id);
    }

    @PostMapping("/post")
    public void addCar(@RequestBody Car car)
    {
        carList.add(car);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") Integer id)
    {

        Car car1 = new Car(id,"miret","muratso",22);
        Car car2 = new Car(id,"bartes","ekal",45);
        Car car3 = new Car(id,"warenka","Birotus",28);

        //carList.addAll(Arrays.asList(car1));
       // carList.addAll(Arrays.asList(car2));
        //carList.addAll(Arrays.asList(car3));

        repo.save(car1);
        repo.save(car2);
        repo.save(car3);

        if(carList == null)
        {
            return new ResponseEntity<Car>(HttpStatus.METHOD_NOT_ALLOWED);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam Integer id)
    {
        Optional<Car> first = carList.stream().filter(param -> param.getId() == id).findFirst();
        first.ifPresent(car -> carList.remove(car));

        return false;
    }


}
