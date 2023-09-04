package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.configuration.MyFirstSampleOSGiConfig;
import com.adobe.aem.guides.wknd.core.models.service.MyFirstSampleOSGiService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = MyFirstSampleOSGiService.class, immediate = true)
@Designate(ocd = MyFirstSampleOSGiConfig.class)
public class MyFirstSampleOSGiImpl implements MyFirstSampleOSGiService{

    private String firstName;
    private String lastName;

    @Activate
    @Modified
    protected void activate(final MyFirstSampleOSGiConfig config){
        firstName = config.firstName();
        lastName = config.lastName();
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
