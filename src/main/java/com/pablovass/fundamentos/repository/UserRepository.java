package com.pablovass.fundamentos.repository;
import com.pablovass.fundamentos.dto.UserDto;
import com.pablovass.fundamentos.entity.User;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //ESTO ES JPQL

    // este area siempre esta bacia siempre y cuando no tengamos que usar jpql o utilizar o sobrescribir los metodos de las clases estendidas
    @Query("Select u from User u WHERE u.email=?1")
    Optional<User>findByUserEmail(String email); // ESTO ES LA DEFINICION DE UNA CONSULTA OSEA UN QUERY METHOD

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%") //VAMOS A ORDENAR POR ORDEN
    List<User> findByAndSort(String name, Sort sort);

    // ESTO ES QUERY METHOD
    List<User>findByName(String name);
    Optional<User>findByEmailAndName(String email,String name);

    //… where x.firstname like ?1
    List<User> findByNameLike(String name);
    List<User> findByNameOrEmail(String name,String email);

    //… where x.birthDate between ?1 and ?2
    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //… where x.age = ?1 order by x.lastname desc
    List<User> findByNameLikeOrderByIdDesc(String name);
   // List<User> findByNameContainingOrderByIdDesc(String name);
/*
*  esta query no me esta funcionando
*   @Query("SELECT new com.pablovass.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)" +
            "FROM User u"  +
            "where u.birthDate=:parametroFecha"+
            "and u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

* */
    List<User> findByNameOrderById(String name);

    List<User> findByNameContaining(String name);


    //… where x.name = ?1 and x.email = ?2
    Optional<User> findUsersByNameAndAndEmail(String name, String email);

    //… where x.name = ?1 or x.email = ?2
    Optional<User> findUsersByNameOrAndEmail(String name, String email);




    //… where x.age = ?1 order by x.lastname desc
    List<User> findByNameContainingOrderByIdDesc(String name);

    //Using Named Parameters
   // @Query("select u from User u where u.name = :name or u.email = :email")
    //Optional<User> findByNameOrEmail(@Param("name") String name,
      //                               @Param("email") String email);

}
