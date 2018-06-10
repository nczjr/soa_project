package com.agh.soa.game.converter;

import com.agh.soa.CategoryType;
import com.agh.soa.game.controller.CreateController;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
public class CategoryTypeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{createController}", CreateController.class);

        CreateController bean = (CreateController) vex.getValue(facesContext.getELContext());
        return bean.getCategoryTypeById(Integer.valueOf(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        CategoryType categoryType = (CategoryType) o;
        return String.valueOf(categoryType.getId()) ;
    }
}
