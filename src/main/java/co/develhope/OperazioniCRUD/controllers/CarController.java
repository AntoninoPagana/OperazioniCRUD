package co.develhope.OperazioniCRUD.controllers;

import co.develhope.OperazioniCRUD.entities.Car;
import co.develhope.OperazioniCRUD.repositories.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarRepo carRepo;

    //crea nuova Car
    @PostMapping("/create")
    public Car addCar(Car carToAdd) {
        Car carAdded = carRepo.save(carToAdd);
        return carAdded;
    }

    //restituisce la lista di tutte le Car
    @GetMapping("/readAll")
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    //restituisce una singola Car - se id non è presente in db (usa existsById())
    //restituisce Car vuota
    @GetMapping("/readSingle")
    public Optional<Car> getCarById(Long id) {
        if (carRepo.existsById(id)) {
            Optional<Car> carToReturn = carRepo.findById(id);
            return carToReturn;
        } else {
            return Optional.empty();
        }
    }

    //aggiorna type della Car specifica, identificata da id e passando query param
    //se id non è presente in db (usa existsById()), restituisce Car vuota
    @PutMapping("/update")
    public Optional<Car> updateCar(Car car, Long id) {
        if (carRepo.existsById(id)) {
            Optional<Car> carOptional = carRepo.findById(id);
            carOptional.get().setType(car.getType());
            carOptional.get().setModelName(car.getModelName());
            return carOptional;
        } else {
            return Optional.empty();
        }
    }

    //cancella la Car specifica - se non presente, la risposta deve avere Conflict HTTP status
    @DeleteMapping("/deleteSingle")
    public void deleteCar(Long id) throws Exception {
        if (carRepo.existsById(id)) {
            carRepo.deleteById(id);
        } else {
            throw new Exception("Conflict HTTP Status");
        }
    }

    //cancella tutte le Cars in db
    @DeleteMapping("/deleteAll")
    public void deleteAllCars() {
        carRepo.deleteAll();
    }

}
