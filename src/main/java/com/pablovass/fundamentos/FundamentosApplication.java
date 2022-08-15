package com.pablovass.fundamentos;

import com.pablovass.fundamentos.bean.MyBean;
import com.pablovass.fundamentos.bean.MyBeanWithDependency;
import com.pablovass.fundamentos.component.ComponentDependency;
import com.pablovass.fundamentos.configuration.MyBeanWithProperties;
import com.pablovass.fundamentos.entity.User;
import com.pablovass.fundamentos.pojo.UserPojo;
import com.pablovass.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;

    //@Autowired ya no es nesesario
	/* CON UNA IMPLEMENTACION
	* public FundamentosApplication(ComponentDependency componentDependency) {
		this.componentDependency = componentDependency;
	}
	//CON DOS IMPLEMENTACIONS
public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency) {
	this.componentDependency = componentDependency;
}
	* */
    //CON 2 IMPLEMENTACIONS
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //ejemplosAteriores();
        saveUserInDataBase();
        getInformationJpqlFromUser();
    }

    /*** este metodo tiene todas las consultas slq instaciada */
    private void getInformationJpqlFromUser() {
 /*
        //userRepository.findByUserEmail("roman@gmail.com");
        LOGGER.info("usuario con el findByUserEmail " +
                userRepository.findByUserEmail("Test4@domain.com")
                        .orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

        userRepository.findByAndSort("user", Sort.by("id").descending())
                .stream()
                .forEach(user -> LOGGER.info("usuario con metod sort" + user));
        userRepository.findByName("John")
                .stream()
                .forEach(user -> LOGGER.info("Usuario con query method " + user));

        LOGGER.info("Usuario con query method findByEmailAndName " + userRepository.findByEmailAndName("daniela@domain.com", "Daniela")
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        userRepository.findByNameLike("%J%")
                .stream()
                .forEach(user -> LOGGER.info("usuario findByNameLike " + user));
        userRepository.findByNameOrEmail("user10", null)
                .stream()
                .forEach(user -> LOGGER.info("usuario findByNameOrEmail " + user));
*/
        userRepository.findByBirthDateBetween(LocalDate.of(2021, 03, 15), LocalDate.of(2021, 03, 25))
                .stream()
                .forEach(user -> LOGGER.info("User with method findByBirthDateBetween:" + user));
        userRepository.findByNameLikeOrderByIdDesc("%user%")
                .stream()
                .forEach(user -> LOGGER.info("User with method findByNameLikeOrderByIdDesc:" + user));
/*
*
*   LOGGER.info("el usuario a partir del named parameter es: "
               + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 03, 25),"daniela@domain.com")
               .orElseThrow(()->new RuntimeException("No se encontro el usuario a partir del name parameter")));

* */


    }

    private void saveUserInDataBase() {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 03, 15));
        User user2 = new User("Julie", "julie@domain.com", LocalDate.of(2021, 03, 20));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 03, 25));
        User user4 = new User("Oscar", "oscar@domain.com", LocalDate.now());
        User user5 = new User("Test1", "Test1@domain.com", LocalDate.now());
        User user6 = new User("Test2", "Test2@domain.com", LocalDate.now());
        User user7 = new User("Test3", "Test3@domain.com", LocalDate.now());
        User user8 = new User("Test4", "Test4@domain.com", LocalDate.now());
        User user9 = new User("Test5", "Test5@domain.com", LocalDate.now());
        User user10 = new User("Test6", "Test6@domain.com", LocalDate.now());
        User user11 = new User("Test7", "Test7@domain.com", LocalDate.now());
        User user12 = new User("Test8", "Test8@domain.com", LocalDate.now());
        User user13 = new User("Test9", "Test9@domain.com", LocalDate.now());
        List<User> list = Arrays.asList(user4, user1, user3, user2, user5, user6, user7, user8, user9, user10, user11, user12, user13);
        list.stream().forEach(userRepository::save);
    }

    private void ejemplosAteriores() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + " " + userPojo.getPassword());
        //LOGGER.error("esto es un error del aplicativo");
        try {
            //error
            int value = 10 / 0;
            LOGGER.debug("Mi valor: " + value);
        } catch (Exception e) {
            LOGGER.error("esto es un error al dividir por cero" + e.getMessage());
        }
    }
}
