package remote;
import com.agh.soa.*;

import java.util.List;

public interface RemoteCategoryService {
    List<CategoryType> getCategoryTypes();
    List<ElementType> getElementTypes();
    List getCategoriesByType(Integer typeId);
    List<Category> getAllCategoriesByType(Integer typeId);
    List getElementsByCategoryId(Integer id);
    ElementType getElementTypeById(Integer id);
    void deleteCategory(Category category);
    void deleteElement(Element element);
    void createCategory(Category category);
    void createElement(Element element);
    List<Element> getByElementType(Integer id);
    List<Element> getMostPowerfulElements();
    void editCategory(Category category);
    void editElement(Element element);
    void changePassword(User user, String oldPassword,String newPassword);
    List<User> getUsers();
}
