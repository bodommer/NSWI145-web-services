package cz.cuni.mff.webservices.soap.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.*;

import javax.xml.namespace.QName;
import java.io.IOException;

@WebServlet(name = "verificationClient", value = "/extendedVerify")
public class Intermediary extends HttpServlet {

    private static final String NAMESPACE = "http://cardverification.soap.webservices.mff.cuni.cz/";

    @Override
    public void init() {
        // NOP
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("text/html");

        // print welcome message
        //response.getWriter().println("<html><body><h1>Welcome to the Card Verifier Client!</h1></body></html>");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();

            MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage soapm = mf.createMessage(null, request.getInputStream());

            SOAPHeader header = soapm.getSOAPHeader();

            soapm.getMimeHeaders().setHeader("Content-Type", "text/xml");

            boolean overrideOption = false;

            if (header.hasChildNodes()) {
                Node flag = header.getChildElements(new QName(null, "behavior")).next();
                String flagValue = flag.getAttributes().getNamedItem("override").getNodeValue();

                if (flagValue != null && !flagValue.equals("0")) {
                    overrideOption = true;
                    System.out.println("Validation override option applied - always returning true.");
                } else {
                    System.out.println("No override option applied.");
                }
                header.removeChild(flag);
            } else {
                System.err.println("Expected override options header not found!");
            }

            // execute service call
            String endpoint = "http://127.0.0.1:8080/soap";
            System.out.println(SOAPHelper.getSOAPMessageAsString(soapm));
            System.out.println("Mime headers");
            soapm.getMimeHeaders().getAllHeaders().forEachRemaining(a -> System.out.println(a.getName() + " " + a.getValue()));
            SOAPMessage soapResponse = soapc.call(soapm, endpoint);
            soapc.close();

            SOAPBody responseBody = soapResponse.getSOAPBody();

            if (responseBody.hasFault()) {
                System.out.println(responseBody.getFault().getFaultString());
            } else {

                QName result = new QName(NAMESPACE, "verifyResponse", "ns2");

                SOAPBodyElement serviceResponse = (SOAPBodyElement) responseBody.getChildElements(result).next();
                SOAPBodyElement responseValue = (SOAPBodyElement) serviceResponse.getChildElements().next();

                QName overrideHeader = new QName(NAMESPACE,
                        "overrideApplied");

                if (!responseValue.getValue().isEmpty() && responseValue.getValue().equals("true")) {
                    System.out.println("Card is valid, no override necessary.");

                    soapResponse.getSOAPHeader().addHeaderElement(overrideHeader).addTextNode("false");
                } else {
                    if (overrideOption) {
                        System.out.println("Card is not valid, applying override.");
                        responseBody.getChildElements(result).next().setTextContent("true");
                        soapResponse.getSOAPHeader().addHeaderElement(overrideHeader).addTextNode("true");
                    } else {
                        System.out.println("Card is not valid!");
                        soapResponse.getSOAPHeader().addHeaderElement(overrideHeader).addTextNode("false");
                    }
                }
                soapResponse.writeTo(response.getOutputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        // NOP
    }
}
