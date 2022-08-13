package com.pablovass.fundamentos.configuration;

import com.pablovass.fundamentos.bean.MyBean;
import com.pablovass.fundamentos.bean.MyBeanImplement;
import com.pablovass.fundamentos.bean.MyBeanTwoImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        //return new MyBeanImplement();
        return new MyBeanTwoImplement();
    }
}
