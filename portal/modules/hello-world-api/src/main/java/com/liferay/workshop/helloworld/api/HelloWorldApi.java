package com.liferay.workshop.helloworld.api;

import aQute.bnd.annotation.ProviderType;


@ProviderType
public interface HelloWorldApi {

    void hello(String name);
}