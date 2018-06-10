package com.agh.soa.game.converter;

import com.agh.soa.Category;
import com.agh.soa.game.CreateController;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "elementConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression vex =
                facesContext.getApplication().getExpressionFactory()
                        .createValueExpression(facesContext.getELContext(),
                                "#{createController}", CreateController.class);

        CreateController bean = (CreateController) vex.getValue(facesContext.getELContext());
        return bean.getCategoryById(Integer.valueOf(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Category category = (Category) o;
        return String.valueOf(category.getId()) ;
    }
}
