package car_rent.controller;

import car_rent.model.User;
import car_rent.service.UserService;
import car_rent.utils.response.PageWrapper;
import car_rent.utils.response.Res;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        Page<User> res = userService.getAll(name, pageable);
        PageWrapper<User> result = new PageWrapper<>(res);
        return Res.rendeerJson(
                result,
                "FOUND",
                HttpStatus.OK
        );
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
