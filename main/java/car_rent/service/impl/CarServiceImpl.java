package car_rent.service.impl;

import car_rent.model.Brand;
import car_rent.model.Car;
import car_rent.repository.CarRepository;
import car_rent.service.BrandService;
import car_rent.service.CarService;
import car_rent.utils.SearchCarRequest;
import car_rent.utils.dto.CarRequestDTO;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final BrandService brandService;

    @Override
    public List<Car> getAll(SearchCarRequest request) {
        Specification<Car> specification = getCarSpecification(request);
        return carRepository.findAll(specification);
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

    private Specification<Car> getCarSpecification(SearchCarRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getId() != null){
                Predicate idPredicate = criteriaBuilder.equal(
                        root.get("id"),
                        request.getId()
                );
                predicates.add(idPredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
