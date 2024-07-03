package car_rent.service.impl;

import car_rent.model.Brand;
import car_rent.model.Car;
import car_rent.repository.CarRepository;
import car_rent.service.BrandService;
import car_rent.service.CarService;
import car_rent.utils.dto.CarRequestDTO;
import car_rent.utils.specification.CarSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandService brandService;

    @Override
    public Page<Car> getAll(String name, Boolean available, Pageable pageable) {
        Specification<Car> specification = CarSpecification.getCarSpecification(name, available);
        return carRepository.findAll(specification, pageable);
    }

    @Override
    public Car getOne(Integer id) {
        return carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car id is not found"));
    }

    @Override
    public Car create(CarRequestDTO request) {
        Car car = new Car();
        Brand brand = brandService.getOne(request.getBrand_id());

        car.setName(request.getName());
        car.setBrand(brand);
        car.setAvailable(request.getAvailable());;
        return carRepository.save(car);
    }

    @Override
    public Car update(Integer id, Car request) {
        Car car = getOne(id);
        car.setName(request.getName());
        car.setAvailable(request.getAvailable());
        carRepository.save(car);
        return car;
    }

    @Override
    public void delete(Integer id) {
        carRepository.deleteById(id);
    }
}
