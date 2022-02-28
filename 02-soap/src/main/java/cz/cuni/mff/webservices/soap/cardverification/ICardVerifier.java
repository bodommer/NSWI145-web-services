package cz.cuni.mff.webservices.soap.cardverification;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface ICardVerifier {

    /**
     * Verifies that the student card used is valid.
     *
     * @param code the code of the card to be verified
     * @return true if the code has 14 chars, starts with an S, second char is an even digit; false otherwise
     */
    @WebMethod
    public boolean verify(String code);
}
