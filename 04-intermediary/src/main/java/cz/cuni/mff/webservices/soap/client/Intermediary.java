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

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage(new MimeHeaders(), request.getInputStream());

            SOAPHeader header = soapm.getSOAPHeader();

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
            String endpoint = "http://127.0.0.1:8000/verification";
            SOAPMessage soapResponse = soapc.call(soapm, endpoint);
            soapc.close();

            SOAPBody responseBody = soapResponse.getSOAPBody();

            if (responseBody.hasFault()) {
                System.out.println(responseBody.getFault().getFaultString());
            } else {

                QName result = new QName(NAMESPACE, "verifyResponse", "ns2");

                SOAPBodyElement serviceResponse = (SOAPBodyElement) responseBody.getChildElements(result).next();
                SOAPBodyElement responseValue = (SOAPBodyElement) serviceResponse.getChildElements().next();

                QName overrideHeader = new QName(NAMESPACE,"overrideApplied");

                String overrideInfo = "false";
                if (!responseValue.getValue().isEmpty() && responseValue.getValue().equals("true")) {
                    System.out.println("Card is valid, no override necessary.");
                } else {
                    if (overrideOption) {
                        System.out.println("Card is not valid, applying override.");
                        responseBody.getChildElements(result).next().setTextContent("true");
                        overrideInfo = "true";
                    } else {
                        System.out.println("Card is not valid!");
                    }
                }

                // add info back to response
                SOAPHeader responseHeader = soapResponse.getSOAPPart().getEnvelope().getHeader();
                if (responseHeader == null) {
                    responseHeader = soapResponse.getSOAPPart().getEnvelope().addHeader();
                }
                responseHeader.addHeaderElement(overrideHeader).addTextNode(overrideInfo);

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
