import com.agh.soa.CategoryType;
import com.agh.soa.Service;
import com.agh.soa.SoapServiceService;

public class SoapClient {

    public static void main(String[] args) {
        Service service = new SoapServiceService().getSoapServicePort();
        service.createCategoryType(new CategoryType());
    }
}
