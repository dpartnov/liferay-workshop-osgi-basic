package com.liferay.workshop.helloworld.api;

import aQute.bnd.annotation.ProviderType;


//The interface’s @ProviderType annotation tells the service registry that anything implementing the interface is a provider.
@ProviderType
public interface HelloWorldApi {

    String hello(String name);
}