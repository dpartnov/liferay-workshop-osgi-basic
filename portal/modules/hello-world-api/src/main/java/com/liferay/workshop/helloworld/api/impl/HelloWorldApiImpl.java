package com.liferay.workshop.helloworld.api.impl;

import com.liferay.workshop.helloworld.api.HelloWorldApi;
import org.osgi.service.component.annotations.Component;

//@Component declares the class a component, providing a particular capability.
//The @Component annotation and its service = HelloWorldApi.class attribute make the HelloWorldApiImpl class a HelloWorldApi service provider.
@Component(
        property = {
                "osgi.command.function=hello",
                "osgi.command.scope=hello-world-api"},
        service = HelloWorldApi.class
)
public class HelloWorldApiImpl implements HelloWorldApi {

    @Override
    public String hello(String name) {
        return String.format("Hello %s !", name);
    }
}
