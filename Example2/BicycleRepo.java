package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_H2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepo extends JpaRepository<Bicycle,Integer> {
}

