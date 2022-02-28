package cz.cuni.mff.webservices.soap.saveapplication;

import jakarta.jws.WebService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@WebService(endpointInterface = "cz.cuni.mff.webservices.soap.saveapplication.IApplicationPersister")
public class ApplicationPersister implements IApplicationPersister {

    private static final Random random = new Random();

    @SuppressWarnings("S2142")
    public boolean persist(String firstName, String lastName, String accommodationStart, String accommodationEnd,
                           int pricePerNight, String room) throws InterruptedException {
        Thread.sleep(200); // simulate doing something :)
        if (random.nextInt(5) == 0) {
            System.out.printf("Unable to store reservation of %s %s into the system. Try again!%n", firstName,
                    lastName);
            return false;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date start;
            Date end;
            try {
                start = dateFormat.parse(accommodationStart);
                end = dateFormat.parse(accommodationEnd);
            } catch (ParseException e) {
                System.out.println("Persisting the application failed - invalid date format.");
                return false;
            }
            System.out.printf("Successfully saved accommodation for %s %s from %s to %s in room %s. Total price to be" +
                            " paid is %d CZK.%n", firstName, lastName, accommodationStart, accommodationEnd, room,
                    TimeUnit.DAYS.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS) * pricePerNight);
            return true;
        }
    }
}