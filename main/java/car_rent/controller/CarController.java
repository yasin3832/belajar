package car_rent.controller;

import car_rent.model.Car;
import car_rent.service.CarService;
import car_rent.utils.SearchCarRequest;
import car_rent.utils.dto.CarRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car create(@RequestBody CarRequestDTO request){
        return carService.create(request);
    }

    @GetMapping
    public List<Car> getAll(@RequestParam(required = false) Integer id){
        SearchCarRequest request = new SearchCarRequest();
        request.setId(id);
        return carService.getAll(request);
    }

    @GetMapping("/{id}")
    public Car getOne(@PathVariable Integer id){
        return carService.getOne(id);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Integer id,@RequestBody Car request){
        return carService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        carService.delete(id);
    }
}
