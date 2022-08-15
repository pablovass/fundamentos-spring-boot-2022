package com.pablovass.fundamentos.service;
import com.pablovass.fundamentos.entity.User;
import com.pablovass.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.transaction.annotation.Transactional;
/**
 * ERROR Y ARREGLAR
 * */
import javax.transaction.Transactional;
import java.util.List;

public class UserService {
    private final Log LOG= LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void saveTransactional(List<User>users){
        users.stream()
                .peek(user -> LOG.info("Usuario insertado "+user))
                .forEach(userRepository::save);
    }
    public  List<User>getAllUsers(){
        return  userRepository.findAll();
    }
}
