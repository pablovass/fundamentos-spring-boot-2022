package com.pablovass.fundamentos;

import com.pablovass.fundamentos.bean.MyBean;
import com.pablovass.fundamentos.bean.MyBeanWithDependency;
import com.pablovass.fundamentos.component.ComponentDependency;
import com.pablovass.fundamentos.configuration.MyBeanWithProperties;
import com.pablovass.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
private  final Log LOGGER= LogFactory.getLog(FundamentosApplication.class);
 private ComponentDependency componentDependency;
 private MyBean myBean;
private MyBeanWithDependency myBeanWithDependency;
private  MyBeanWithProperties myBeanWithProperties;
private UserPojo userPojo;
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
public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency , MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo) {
	this.componentDependency = componentDependency;
	this.myBean=myBean;
	this.myBeanWithDependency=myBeanWithDependency;
	this.myBeanWithProperties =myBeanWithProperties;
	this.userPojo=userPojo;
}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
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
