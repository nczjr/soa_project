package com.agh.soa.game.converter;

import com.agh.soa.Element;
import com.agh.soa.ElementType;
import com.agh.soa.game.CreateController;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "elementConverter")
public class ElementConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{createController}", CreateController.class);

        CreateController bean = (CreateController) vex.getValue(facesContext.getELContext());
        return bean.getElements()
                .stream()
                .filter(element -> element.getId()== Integer.valueOf(s)).findAny().get();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Element element = (Element) o;
        return String.valueOf(element.getId()) ;
    }
}

