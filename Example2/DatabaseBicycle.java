package spring_boot.EXAMPLE_SPRING_BOOT.SECURITY_H2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DatabaseBicycle {

    @Autowired
    private BicycleRepo repo;

    @EventListener(ApplicationReadyEvent.class)
    public void saveDB()
    {
        Bicycle bicycle = new Bicycle("Empolio", "Franko", "34SDF", 2,10,3400,"30.05.2007", "KAN");
        Bicycle bicycle2 = new Bicycle("Ladko", "Berosowic", "678CV", 7,9,3414,"30.07.2005", "Brz");
        Bicycle bicycle3 = new Bicycle("Eldako", "Munic", "34ERT", 3,9,5222,"30.08.2010", "ITE");
        Bicycle bicycle4 = new Bicycle("Firento", "Munio", "54FVF", 4,7,3364,"30.10.2008", "DEN");
        Bicycle bicycle5 = new Bicycle("Seriannoe", "Dekato", "ETYUI", 7,11,3412,"30.12.2001", "WIT");
        Bicycle bicycle6 = new Bicycle("Mudas", "Pawas", "EE3456", 5,6,9987,"30.11.2018", "KAN");


        repo.save(bicycle);
        repo.save(bicycle2);
        repo.save(bicycle3);
        repo.save(bicycle4);
        repo.save(bicycle5);
        repo.save(bicycle6);

    }
}

