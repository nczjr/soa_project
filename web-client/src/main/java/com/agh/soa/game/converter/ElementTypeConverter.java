package com.agh.soa.game.converter;

import com.agh.soa.ElementType;
import com.agh.soa.game.controller.CreateController;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "elementTypeConverter")
public class ElementTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{createController}", CreateController.class);

        CreateController bean = (CreateController) vex.getValue(facesContext.getELContext());
        return bean.getElementTypes()
                .stream()
                .filter(elementType -> elementType.getId()== Integer.valueOf(s))
                .findAny().get();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        ElementType elementType = (ElementType) o;
        return String.valueOf(elementType.getId()) ;
    }
}
