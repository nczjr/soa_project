package remote;
import com.agh.soa.*;

import java.util.List;

public interface RemoteCategoryService {
    List<CategoryType> getCategoryTypes();
    List<ElementType> getElementTypes();
    List getCategoriesByType(Integer typeId);
    List getElementsByCategoryId(Integer id);
    List getAllCategories();
    ElementType getElementTypeById(Integer id);
    void deleteCategory(Category category);
    void deleteElement(Element element);
    User findUserById(Integer id);
    void createCategory(Category category);
    void createElement(Element element);
    List<Element> getByElementType(Integer id);
    void editCategory(Category category);
    void editElement(Element element);

    List<Element> getElements();
}
