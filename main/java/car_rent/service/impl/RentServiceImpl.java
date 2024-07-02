package car_rent.service.impl;

import car_rent.model.Car;
import car_rent.model.Rent;
import car_rent.model.User;
import car_rent.repository.RentRepository;
import car_rent.service.CarService;
import car_rent.service.RentService;
import car_rent.service.UserService;
import car_rent.utils.SearchCarRequest;
import car_rent.utils.dto.RentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final CarService carService;
    private final UserService userService;


    @Override
    public Rent create(RentRequestDTO request) {

        User user = userService.getOne(request.getUser_id());
        Car car = carService.getOne(request.getCar_id());

        if (car.getAvailable() == true){
            Rent createdRent = new Rent();
            createdRent.setCompleted(request.getCompleted());
            createdRent.setCar(car);
            createdRent.setUser(user);
            if (request.getCompleted() == true){
                car.setName(car.getName());
                car.setAvailable(true);
                carService.update(car.getId(), car);
            } else if (request.getCompleted() == false) {
                car.setName(car.getName());
                car.setAvailable(false);
                carService.update(car.getId(), car);
            }
            return rentRepository.save(createdRent);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent getOne(Integer id) {
        return rentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rent id is not found"));
    }

    @Override
    public Rent update(Integer id, RentRequestDTO request) {
        Rent rent = this.getOne(id);
        User user = userService.getOne(request.getUser_id());
        Car car = carService.getOne(request.getCar_id());
        rent.setCompleted(request.getCompleted());
        rent.setCar(car);
        rent.setUser(user);
        if (request.getCompleted() == true){
            car.setName(car.getName());
            car.setAvailable(true);
            carService.update(car.getId(), car);
        } else if (request.getCompleted() == false) {
            car.setName(car.getName());
            car.setAvailable(false);
            carService.update(car.getId(), car);
        }
        rentRepository.save(rent);
        return rent;
    }

    @Override
    public void delete(Integer id) {

    }
}
