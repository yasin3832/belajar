package car_rent.service.impl;

import car_rent.model.User;
import car_rent.repository.UserRepository;
import car_rent.service.UserService;
import car_rent.utils.response.PageWrapper;
import car_rent.utils.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public Page<User> getAll(String name, Pageable pageable) {
        Specification<User> specification = UserSpecification.getUserSpecification(name);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User getOne(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id is not found"));
    }

    @Override
    public User create(User request) {
        return userRepository.save(request);
    }

    @Override
    public User update(Integer id, User request) {
        User user = this.getOne(id);
        user.setName(request.getName());
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
