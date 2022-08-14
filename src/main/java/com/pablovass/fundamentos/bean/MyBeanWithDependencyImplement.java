package com.pablovass.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    Log LOGGER= LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private  MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Hemos ingresado al metodo PrintWithDependency");
        int numero =1;
        LOGGER.debug("el numero enviado como parametro a la dependencia operation es: "+numero);
        System.out.println(myOperation.sum(numero));
        System.out.println(" Hola desde la implemetacion de un bean con dependencia");

    }
}
