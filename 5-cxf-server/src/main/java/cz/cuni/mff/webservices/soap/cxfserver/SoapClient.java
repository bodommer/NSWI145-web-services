package cz.cuni.mff.webservices.soap.cxfserver;

import javax.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.URL;

public class SoapClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://127.0.0.1:8000/verification?wsdl");
        QName qname = new QName("http://cxf.soap.webservices.mff.cuni.cz/", "CardVerifierService");
        Service service = Service.create(url, qname);
        ICardVerifier verifier = service.getPort(ICardVerifier.class);
        System.out.println(verifier.verify("S4205839658394")); // returns true
    }

}
