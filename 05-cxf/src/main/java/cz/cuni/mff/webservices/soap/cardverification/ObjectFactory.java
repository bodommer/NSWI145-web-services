
package cz.cuni.mff.webservices.soap.cardverification;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.cuni.mff.webservices.soap.cardverification package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddCard_QNAME = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "addCard");
    private final static QName _VerifyResponse_QNAME = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "verifyResponse");
    private final static QName _AddCardResponse_QNAME = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "addCardResponse");
    private final static QName _Verify_QNAME = new QName("http://cardverification.soap.webservices.mff.cuni.cz/", "verify");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cuni.mff.webservices.soap.cardverification
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddCardResponse }
     * 
     */
    public AddCardResponse createAddCardResponse() {
        return new AddCardResponse();
    }

    /**
     * Create an instance of {@link Verify }
     * 
     */
    public Verify createVerify() {
        return new Verify();
    }

    /**
     * Create an instance of {@link AddCard }
     * 
     */
    public AddCard createAddCard() {
        return new AddCard();
    }

    /**
     * Create an instance of {@link VerifyResponse }
     * 
     */
    public VerifyResponse createVerifyResponse() {
        return new VerifyResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cardverification.soap.webservices.mff.cuni.cz/", name = "addCard")
    public JAXBElement<AddCard> createAddCard(AddCard value) {
        return new JAXBElement<AddCard>(_AddCard_QNAME, AddCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cardverification.soap.webservices.mff.cuni.cz/", name = "verifyResponse")
    public JAXBElement<VerifyResponse> createVerifyResponse(VerifyResponse value) {
        return new JAXBElement<VerifyResponse>(_VerifyResponse_QNAME, VerifyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cardverification.soap.webservices.mff.cuni.cz/", name = "addCardResponse")
    public JAXBElement<AddCardResponse> createAddCardResponse(AddCardResponse value) {
        return new JAXBElement<AddCardResponse>(_AddCardResponse_QNAME, AddCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Verify }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cardverification.soap.webservices.mff.cuni.cz/", name = "verify")
    public JAXBElement<Verify> createVerify(Verify value) {
        return new JAXBElement<Verify>(_Verify_QNAME, Verify.class, null, value);
    }

}
