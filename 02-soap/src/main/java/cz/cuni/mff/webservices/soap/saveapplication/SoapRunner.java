package cz.cuni.mff.webservices.soap.saveapplication;

import jakarta.xml.ws.Endpoint;

public class SoapRunner {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8000/application-manager",
                new cz.cuni.mff.webservices.soap.saveapplication.ApplicationPersister());
    }
}
