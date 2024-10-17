package com.liferay.workshop.helloworld.api.impl;

import com.liferay.workshop.helloworld.api.HelloWorldApi;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {"osgi.command.function=hello", "osgi.command.scope=hello-world-api"},
        service = HelloWorldApi.class
)
public class HelloWorldApiImpl implements HelloWorldApi {

    @Override
    public String hello(String name) {
        return String.format("Hello %s !", name);
    }
}
