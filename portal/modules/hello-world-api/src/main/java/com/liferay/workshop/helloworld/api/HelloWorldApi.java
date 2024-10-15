package com.liferay.workshop.helloworld.api;

import aQute.bnd.annotation.ProviderType;


@ProviderType
public interface HelloWorldApi {

    String hello(String name);
}