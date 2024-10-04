package co.pragra.productmanager.newproductmanager.repo;


import co.pragra.productmanager.newproductmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);

    List<User> findALlByFirstNameAndLastName(String firstName, String lastName);

}
