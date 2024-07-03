package car_rent.service;

import car_rent.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> getAll(String name, Pageable pageable);
    User getOne(Integer id);
    User create(User request);
    User update(Integer id,User request);
    void delete(Integer id);

}
