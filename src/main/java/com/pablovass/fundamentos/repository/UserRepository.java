package com.pablovass.fundamentos.repository;
import com.pablovass.fundamentos.entity.User;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    // este area siempre esta bacia siempre y cuando no tengamos que usar jpql o utilizar o sobrescribir los metodos de las clases estendidas
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User>findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%") //VAMOS A ORDENAR POR ORDEN
    List<User> findByAndSort(String name, Sort sort);

    //@Query("select u from User u where u.name like ?1%")
    //List<User> findByAndSort(String name, Sort sort);
}
