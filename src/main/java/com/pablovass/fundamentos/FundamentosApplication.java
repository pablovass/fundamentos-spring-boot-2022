package com.pablovass.fundamentos;

import com.pablovass.fundamentos.bean.MyBean;
import com.pablovass.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
 private ComponentDependency componentDependency;
 private MyBean myBean;
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
public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency ,MyBean myBean ) {
	this.componentDependency = componentDependency;
	this.myBean=myBean;
}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		componentDependency.saludar();
		myBean.print();
	}
}
