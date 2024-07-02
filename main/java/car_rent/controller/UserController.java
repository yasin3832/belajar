package car_rent.controller;

import car_rent.model.User;
import car_rent.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody User request){
        return userService.create(request);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id){
        return userService.getOne(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id,@RequestBody User request){
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }
}
