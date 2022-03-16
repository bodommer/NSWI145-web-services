package cz.cuni.mff.webservices.soap.client;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.*;

import javax.xml.namespace.QName;

@WebServlet("/intermediary")
public class Intermediary extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage(new MimeHeaders(), request.getInputStream());

            SOAPPart soapp = soapm.getSOAPPart();
            SOAPEnvelope soape = soapp.getEnvelope();
            SOAPBody soapb = soape.getBody();

//            SOAPHeader header = soapm.getSOAPHeader();

//            Header removal
//            header.detachNode();


            QName name = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "CardVerifier");
            SOAPElement soapel = soapb.addBodyElement(name);

            soapel.addChildElement(
                    new QName("", "arg0")).addTextNode("S4204567345678");
            String endpoint = "http://127.0.0.1:8000/verification";
            SOAPMessage soapResponse = soapc.call(soapm, endpoint);
            SOAPBody responseBody = soapResponse.getSOAPBody();

            if (responseBody.hasFault()) {
                System.out.println(responseBody.getFault().getFaultString());
            } else {

                QName result = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "result");

                SOAPBodyElement finalResponse = (SOAPBodyElement)
                        responseBody.getChildElements(result).next();

                System.out.println(finalResponse.getValue());
            }
            soapc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
