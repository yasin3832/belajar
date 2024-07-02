package car_rent.service;

import car_rent.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getOne(Integer id);
    User create(User request);
    User update(Integer id,User request);
    void delete(Integer id);

}
