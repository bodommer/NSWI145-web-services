package cz.cuni.mff.webservices.soap.cardverification;

import org.apache.juddi.v3.annotations.UDDIService;
import org.apache.juddi.v3.annotations.UDDIServiceBinding;

import cz.cuni.mff.webservices.soap.cardverification.ICardVerifier;

import javax.jws.WebService;

@UDDIService(
		businessKey="uddi:${keyDomain}:${department}",
		serviceKey="uddi:${keyDomain}:service1", 
		description = "Card Verification test service")
@UDDIServiceBinding(
		bindingKey="uddi:${keyDomain}:service1-wsdl",
	    description="WSDL endpoint for the cardVerfier${department} Service. This service is used for "
				  + "testing the jUDDI annotation functionality",
	    accessPointType="wsdlDeployment",
	    accessPoint="http://${serverName}:${serverPort}/uddi-annotations/services/service?wsdl")
@WebService(
		endpointInterface = "cz.cuni.mff.webservices.soap.cardverification.ICardVerifier",
        serviceName = "verify")

public class CardVerifier implements ICardVerifier {

    public boolean verify(String code) {
        System.out.println("Request accepted");
        return code.startsWith("S")                                 // starts with 'S'
                && code.length() == 14                              // 14 chars
                && (code.charAt(1) >= 48 && code.charAt(1) <= 57)   // second char is a digit
                && (code.charAt(1) - 48) % 2 == 0;                  // random true/false -> to mock verification in DB
    }

//    public boolean addCard(String code) {
//        return code.length() == 14;
//    }
}