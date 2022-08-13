package com.pablovass.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements  ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("saludo desde mi seguinda implementacion");
    }
}
