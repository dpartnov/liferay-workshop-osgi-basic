package com.liferay.workshop.helloworld.api.impl;

import com.liferay.workshop.helloworld.api.HelloWorldApi;

public class HelloWorldApiImpl implements HelloWorldApi {

    @Override
    public void hello(String name) {
        System.out.println("Hello " + name);
    }
}
