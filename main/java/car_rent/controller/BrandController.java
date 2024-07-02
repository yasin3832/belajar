package car_rent.controller;

import car_rent.model.Brand;
import car_rent.repository.BrandRepository;
import car_rent.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public Brand create(@RequestBody Brand request){
        return brandService.create(request);
    }

    @GetMapping
    public List<Brand> getAll(){
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public Brand getOne(@PathVariable Integer id){
        return brandService.getOne(id);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Integer id,@RequestBody Brand brand){
        return brandService.update(id, brand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        brandService.delete(id);
    }
}
