package car_rent.service;

import car_rent.model.Car;
import car_rent.utils.SearchCarRequest;
import car_rent.utils.dto.CarRequestDTO;

import java.util.List;

public interface CarService {
    List<Car> getAll(SearchCarRequest request);
    Car getOne(Integer id);
    Car create(CarRequestDTO request);
    Car update(Integer id,Car request);
    void delete(Integer id);

}
