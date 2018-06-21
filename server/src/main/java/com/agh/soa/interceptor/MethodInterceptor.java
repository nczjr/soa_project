package com.agh.soa.interceptor;

import com.agh.soa.Element;
import com.agh.soa.dao.ElementDAO;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MethodInterceptor {


    @Inject
    private ElementDAO elementDAO;

    @AroundInvoke
    public Object interceptorMethod(InvocationContext ictx) throws Exception{
        Element element = (Element) ictx.getParameters()[0];
        Element powerfulElement = elementDAO.getMostPowerfulElement(element.getElementTypesByTypeId().getId());
        if (powerfulElement != null && element.getPowerValue()> powerfulElement.getPowerValue()) {
            element.setPowerValue(powerfulElement.getPowerValue());
        }
        ictx.proceed();
        return element;
    }
}
