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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
private  final Log LOGGER= LogFactory.getLog(FundamentosApplication.class);
 private ComponentDependency componentDependency;
 private MyBean myBean;
private MyBeanWithDependency myBeanWithDependency;
private  MyBeanWithProperties myBeanWithProperties;
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
public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency , MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,UserRepository userRepository) {
	this.componentDependency = componentDependency;
	this.myBean=myBean;
	this.myBeanWithDependency=myBeanWithDependency;
	this.myBeanWithProperties =myBeanWithProperties;
	this.userPojo=userPojo;
	this.userRepository=userRepository;
}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//ejemplosAteriores();
		saveUserInDataBase();
	}

	private void  saveUserInDataBase(){
		User user1= new User("John","John@gmail.com", LocalDate.of(2021,03,20));
		User user2= new User("Miguel","miguel@gmail.com", LocalDate.of(2020,02,20));
		User user3= new User("Jo","Jo@gmail.com", LocalDate.of(2019,01,20));
		User user4= new User("Maria","maria@gmail.com", LocalDate.of(2019,01,20));
		User user5= new User("Jose","Jose@gmail.com", LocalDate.of(2019,01,20));
		User user6= new User("roman","roman@gmail.com", LocalDate.of(2019,01,20));

		List<User>list= Arrays.asList(user1,user2,user3,user4,user5,user6);

		list.stream().forEach(userRepository::save);
	}
	private  void ejemplosAteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+" "+userPojo.getPassword());
		//LOGGER.error("esto es un error del aplicativo");
		try{
			//error
			int value=10/0;
			LOGGER.debug("Mi valor: "+ value);
		}catch (Exception e){
			LOGGER.error("esto es un error al dividir por cero" + e.getMessage());
		}
	}
}
