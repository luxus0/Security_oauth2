package spring_boot.spring_boot.Security.Example5.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import spring_boot.spring_boot.Security.Example5.Entity.Car;

import java.util.List;

@Repository
public interface CarRepo  extends JpaRepository<Car,Long> {
    public  List<Car> findByMark(@RequestParam String mark);
    public  List<Car> findByModel(@RequestParam String model);
}
