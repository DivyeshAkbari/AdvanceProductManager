package co.pragra.productmanager.newproductmanager.services;


import co.pragra.productmanager.newproductmanager.entity.User;
import co.pragra.productmanager.newproductmanager.exception.InvalidUserDataException;
import co.pragra.productmanager.newproductmanager.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    
    public User CreateUser(User user){
        if(user==null){
            throw new InvalidUserDataException("User Can not be null");
        }
        if(user.getFirstName()==null || user.getFirstName().isEmpty()){
            throw new InvalidUserDataException("First can not be null or empty");
        }
        user.setCreatedDate(new Date());
        user.setUpdateDate(new Date());
        return userRepo.save(user);
    }

    public User UpdateUser(User user){
        if(user==null){
            throw new InvalidUserDataException("User Can not be null");
        }

        Optional<User> optionalUser = userRepo.findById(user.getId());
        optionalUser.orElseThrow(() -> new InvalidUserDataException("User doesn't exist."));

        User dbUser = optionalUser.get();
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        dbUser.setUpdateDate(user.getUpdateDate());

        return userRepo.save(dbUser);
    }

    public List<User> getAllByFilters(Optional<String> firstName,
                                      Optional<String> lastName) {

        if (firstName.isPresent() && lastName.isPresent()){
            return userRepo.findALlByFirstNameAndLastName(firstName.get(), lastName.get());
        }

        if (firstName.isPresent()){
            return userRepo.findAllByFirstName(firstName.get());
        }

        if (lastName.isPresent()){
            return userRepo.findAllByLastName(lastName.get());
        }

        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
       return this.userRepo.findById(id);
    }
}
