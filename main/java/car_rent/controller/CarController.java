package car_rent.controller;

import car_rent.model.Car;
import car_rent.service.CarService;
import car_rent.utils.dto.CarRequestDTO;
import car_rent.utils.response.PageWrapper;
import car_rent.utils.response.Res;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean available,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        Page<Car> res = carService.getAll(name, available, pageable);
        PageWrapper<Car> result = new PageWrapper<>(res);
        return Res.rendeerJson(
                result,
                "FOUND",
                HttpStatus.OK
        );
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
