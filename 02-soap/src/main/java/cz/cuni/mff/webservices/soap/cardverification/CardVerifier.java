package cz.cuni.mff.webservices.soap.cardverification;

import jakarta.jws.WebService;

@WebService(endpointInterface = "cz.cuni.mff.webservices.soap.cardverification.ICardVerifier")
public class CardVerifier implements ICardVerifier {

    public boolean verify(String code) {
        return code.startsWith("S")                                 // starts with 'S'
                && code.length() == 14                              // 14 chars
                && (code.charAt(1) >= 48 && code.charAt(1) <= 57)   // second char is a digit
                && (code.charAt(1) - 48) % 2 == 0;                  // random true/false -> to mock verification in DB
    }
}