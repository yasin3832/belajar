package car_rent.service;

import car_rent.model.Car;
import car_rent.model.Rent;
import car_rent.repository.RentRepository;
import car_rent.utils.dto.RentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RentService {
    Rent create(RentRequestDTO request);
    List<Rent> getAll();
    Rent getOne(Integer id);
    Rent update(Integer id, RentRequestDTO request);
    void delete(Integer id);
}
