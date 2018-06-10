package remote;
import com.agh.soa.*;

import java.util.List;

public interface RemoteCategoryService {
    List<CategoryType> getCategoryTypes();
    List getCategoriesByType(int typeId);
    List getElementsByCategoryId(int id);
    List getAllCategories();
    ElementType getElementTypeById(int id);
    void deleteCategory(Category category);
    void deleteElement(Element element);
    User findUserById(Integer id);
    void createCategory(Category category);
    void createElement(Element element);

    List<Element> getElements();
}
