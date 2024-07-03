package car_rent.service;

import car_rent.model.Car;
import car_rent.utils.dto.CarRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    Page<Car> getAll(String name, Boolean available, Pageable pageable);
    Car getOne(Integer id);
    Car create(CarRequestDTO request);
    Car update(Integer id,Car request);
    void delete(Integer id);

}
