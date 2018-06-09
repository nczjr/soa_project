package remote;
import com.agh.soa.Category;
import com.agh.soa.Element;

import java.util.List;

public interface RemoteCategoryService {
    List getCategoryTypes();
    List getCategoriesByType(int typeId);
    List getParametersByCategoryId(int id);
    List getElementsByCategoryId(int id);
    List getIntParametersByElementId(int id);
    List getStringParametersByElementId(int id);

    List<Element> getElements();
}
