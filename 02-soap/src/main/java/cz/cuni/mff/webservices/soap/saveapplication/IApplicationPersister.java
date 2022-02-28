package cz.cuni.mff.webservices.soap.saveapplication;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.Date;

@WebService
public interface IApplicationPersister {

    /**
     * Persists an accommodation application in the database.
     *
     * @param firstName          applicant name
     * @param lastName           applicant surname
     * @param accommodationStart date of the application start - format to be used is dd/MM/yyyy
     * @param accommodationEnd   date of the application end - format to be used is dd/MM/yyyy
     * @param pricePerNight      agreed price per night
     * @param room               room number
     * @return true if the process went successfully, false otherwise
     * @throws InterruptedException when thread's sleep() is interrupted
     */
    @WebMethod
    public boolean persist(String firstName, String lastName, String accommodationStart, String accommodationEnd,
                           int pricePerNight, String room) throws InterruptedException;
}
