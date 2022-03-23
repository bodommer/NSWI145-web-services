package cz.cuni.mff.webservices.soap.cardverification;

import jakarta.xml.ws.Endpoint;

public class SoapRunner {
    public static void main(String[] args) {
        System.out.println("Fuck my life");
        Endpoint.publish("http://127.0.0.1:8000/verification",
                new CardVerifier());
    }
}
