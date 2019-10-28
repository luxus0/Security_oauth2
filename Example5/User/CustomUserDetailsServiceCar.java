package spring_boot.spring_boot.Security.Example5.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring_boot.spring_boot.Security.Example5.Entity.Car;
import spring_boot.spring_boot.Security.Example5.Exception.ResourceNotFoundException;
import spring_boot.spring_boot.Security.Example5.Repo.CarRepo;


import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceCar implements UserDetailsService {

    @Autowired
    private CarRepo repo;
    private UserPrincipal user;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mark) throws UsernameNotFoundException {
        List<Car> car = repo.findByMark(mark).stream().collect(Collectors.toList());

        return UserPrincipal.create(user);
    }


    @Transactional
    public UserDetails LoadUserById(Long id)
    {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("RESOURCE NOT FOUND","id",2));

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserByModel(String model)
    {
        Map<String,Car> map = new HashMap<>();
        map.put(("I"),new Car(1,"markerwe","modeldsdlo",10));
        map.put(("II"),new Car(2,"markerai","modsadsello",15));
        map.put(("III"),new Car(3,"markeret","modasdello",34));
        map.put(("IV"),new Car(4,"markermu","modasdsello",20));
        map.put(("V"),new Car(5,"markerfz","modsadello",34));




        repo.findByModel(model).forEach(System.out::println);

        for(int i = 0; i < map.size(); i ++) {
            for (String key : map.keySet()) {
                for (Car value : map.values()) {
                    System.out.println("KEY: " +key + map.get(i));
                    System.out.println("VALUE mark:" +value.getMark());
                }
            }
        }
        return UserPrincipal.create(user);
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));

    }

}