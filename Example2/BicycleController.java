package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_H2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_JDBC.AddPeopleDB;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class BicycleController {

    @Autowired
    private BicycleRepo repo;

    @GetMapping("/getBicycle")
    public List<Bicycle> getBicycle()
    {
        return repo.findAll();
    }

    @GetMapping("/getBicycle/{id}")
    public Optional<Bicycle> getBicycle(@PathVariable("id")Integer id)
    {
        return repo.findById(id);

    }

    @PostMapping("/addBicycle")
    @ResponseStatus(HttpStatus.OK)
    public @Valid Bicycle addBicycle(@Valid @RequestBody Bicycle bicycle)
    {
        return repo.save(bicycle);
    }

    @PutMapping("/updateBicycle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bicycle updateBicycle(@PathVariable("id") Integer id) {
        return repo.findById(id).map(bic -> {
            bic.setId(bic.getId());
            bic.setName(bic.getName());
            bic.setMark(bic.getMark());
            bic.setModel(bic.getModel());
            bic.setAge(bic.getAge());
            bic.setDateProduction(bic.getDateProduction());
            bic.setDescription(bic.getDescription());
            bic.setPrice(bic.getPrice());
            bic.setWeight(bic.getWeight());

            return repo.save(bic);

        }).get();


    }

    @PatchMapping("/pathBicycle/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Bicycle PathBicycle(@PathVariable("id") Integer id)
    {
        Bicycle bicycle = repo.findById(id).get();
        bicycle.setId(bicycle.getId());
        bicycle.setName(bicycle.getName());
        bicycle.setMark(bicycle.getMark());
        bicycle.setModel(bicycle.getModel());
        bicycle.setPrice(bicycle.getPrice());
        bicycle.setAge(bicycle.getAge());
        bicycle.setDescription(bicycle.getDescription());
        bicycle.setDateProduction(bicycle.getDateProduction());

        repo.save(bicycle);
        return bicycle;
    }

    @DeleteMapping("/deleteBicycle")
    public void deleteBicycle()
    {
        repo.deleteAll();
    }

}
