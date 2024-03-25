package co.develhope.OperazioniCRUD.repositories;

import co.develhope.OperazioniCRUD.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
}
