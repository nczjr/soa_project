package soap;

import com.agh.soa.CategoryType;
import com.agh.soa.ElementType;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Service {

    @WebMethod
    void createCategoryType(CategoryType categoryType);

    @WebMethod
    void createElementType(ElementType elementType);


}