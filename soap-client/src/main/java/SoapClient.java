import com.agh.soa.Element;
import com.agh.soa.Service;
import com.agh.soa.SoapServiceService;

public class SoapClient {

    public static void main(String[] args) {
        Service service = new SoapServiceService().getSoapServicePort();
        Element element = new Element();
        element.setIntValue1(22);
        element.setId(73);
        service.editElement(element);
    }
}
