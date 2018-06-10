package com.agh.soa.game.converter;

import com.agh.soa.ElementType;
import com.agh.soa.User;
import com.agh.soa.game.controller.CreateController;
import com.agh.soa.game.controller.PasswordChangeController;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{passwordChangeController}", PasswordChangeController.class);

        PasswordChangeController bean = (PasswordChangeController) vex.getValue(facesContext.getELContext());
        return bean.getUsers()
                .stream()
                .filter(user -> user.getId()== Integer.valueOf(s))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        User user = (User) o;
        return String.valueOf(user.getId()) ;
    }
}

