package cz.cuni.mff.webservices.soap.client;

import cz.cuni.mff.webservices.soap.cardverification.ICardVerifier;
import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.URL;

public class SoapClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://127.0.0.1:8080/soap?wsdl");
        QName qname = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "CardVerifierService");
        Service service = Service.create(url, qname);
        ICardVerifier verifier = service.getPort(ICardVerifier.class);
        System.out.println(verifier.verify("S4205839658394")); // returns true
    }

}
