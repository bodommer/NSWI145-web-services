package cz.cuni.mff.webservices.soap.cardverification;

import jakarta.xml.ws.Endpoint;

public class SoapRunner {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8000/verify-card",
                new CardVerifier());
    }
}
