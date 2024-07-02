package car_rent.controller;

import car_rent.model.Car;
import car_rent.model.Rent;
import car_rent.service.RentService;
import car_rent.utils.dto.RentRequestDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    public Rent create(@RequestBody RentRequestDTO request){
        return rentService.create(request);
    }

    @GetMapping
    public List<Rent> getRents(){
        return rentService.getAll();
    }

    @GetMapping("/{id}")
    public Rent getOne(@PathVariable Integer id){
        return rentService.getOne(id);
    }

    @PutMapping("/{id}")
    public Rent update(@PathVariable Integer id,@RequestBody RentRequestDTO request){
        return rentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        rentService.delete(id);
    }
}
