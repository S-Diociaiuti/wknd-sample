package com.adobe.aem.guides.wknd.core.models.configuration;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "My First Sample Config ", description = "My First Sample Configuration")
public @interface MyFirstSampleOSGiConfig {

    @AttributeDefinition(name = "first.name", defaultValue = "Mario", description = "First Name")
    String firstName();

    @AttributeDefinition(name = "last.name", defaultValue = "Rossi", description = "Last Name")
    String lastName();
}
