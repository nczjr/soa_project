package soap;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import com.agh.soa.ElementType;
import remote.RemoteCategoryService;
import soap.Service;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SoapService implements Service {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    @Override
    @WebMethod
    public void createCategoryType(CategoryType categoryType) {
        remoteCategoryService.createCategoryType(categoryType);
    }

    @Override
    @WebMethod
    public void createElementType(ElementType elementType) {
        remoteCategoryService.createElementType(elementType);
    }
}