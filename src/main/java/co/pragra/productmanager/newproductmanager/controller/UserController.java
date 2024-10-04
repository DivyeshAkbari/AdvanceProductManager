package co.pragra.productmanager.newproductmanager.controller;

import co.pragra.productmanager.newproductmanager.entity.User;
import co.pragra.productmanager.newproductmanager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user)
    {
        return this.userService.CreateUser(user);
    }

    @GetMapping("/api/user")
    public List<User> findAll(@RequestParam(value = "firstName",required = false) Optional<String> firstName
    ,@RequestParam(value = "lastName",required = false) Optional<String> lastName)
    {
       return this.userService.getAllByFilters(firstName, lastName);
    }

    @GetMapping("/api/user/{id}")
    public Optional<User> findById(@PathVariable("id") Long id)
    {
        return this.userService.findById(id);
    }
}
