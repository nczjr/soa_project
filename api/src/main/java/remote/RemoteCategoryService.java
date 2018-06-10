package remote;
import com.agh.soa.*;

import java.util.List;

public interface RemoteCategoryService {
    List getCategoryTypes();
    List getCategoriesByType(int typeId);
    List getParametersByCategoryId(int id);
    List getElementsByCategoryId(int id);
    List getIntParametersByElementId(int id);
    List getStringParametersByElementId(int id);
    void createCategoryByParameter(IntParameter parameter);
    void createElementByParameters(List<IntParameter> parameters, StringParameter stringParameter);
    List getAllCategories();
    ElementType getElementTypeById(int id);
    String getLabelByCategoryTypeId(int id);
    List getParametersLabelsByCategoryType(int id);
    String getStringLabelByCategoryType(int id);

    List<Element> getElements();
}
