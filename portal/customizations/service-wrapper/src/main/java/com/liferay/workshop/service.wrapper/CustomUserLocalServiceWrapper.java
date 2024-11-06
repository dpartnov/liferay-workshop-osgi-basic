package com.liferay.workshop.service.wrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
import org.osgi.service.component.annotations.Component;

@Component(service = ServiceWrapper.class)
public class CustomUserLocalServiceWrapper extends UserLocalServiceWrapper {

    @Override
    public User getUser(long userId) throws PortalException {
        System.out.println("Invoking get user for userID=" + userId);

        return super.getUser(userId);
    }
}
