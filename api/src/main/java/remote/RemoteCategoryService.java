package remote;
import com.agh.soa.Category;
import com.agh.soa.Element;

import java.util.List;

public interface RemoteCategoryService {
    List<Category> getCategories();
    List getParameterByCategoryId(int id);
    List<Element> getElements();
}
