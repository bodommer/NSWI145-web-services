package cz.cuni.mff.webservices.rest;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/verify")
public class CardVerificationResource {

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("{code}")
    public String verifyCard(@PathParam("code") String code) {
        // 1 -> valid, 0 -> invalid
        return (code.startsWith("S")                                 // starts with 'S'
                && code.length() == 14                              // 14 chars
                && (code.charAt(1) >= 48 && code.charAt(1) <= 57)   // second char is a digit
                && (code.charAt(1) - 48) % 2 == 0) ? "1" : "0";
    }

    @POST
    @Produces({MediaType.TEXT_PLAIN})
    @Path("{code}")
    public String addCard(@PathParam("code") String code) {
        // 1 -> success, 0 -> failure
        return code.length() == 14 ? "1" : "0";
    }

    @PUT
    @Produces({MediaType.TEXT_PLAIN})
    @Path("{code1}/{code2}")
    public String updateCard(@PathParam("code1") String oldCode, @PathParam("code2") String newCode) {
        // this would check that there is some oldCode in the DB and then replace it with the newCode,
        // newCode returned if replacement was successful, oldCode if it was not found in the DB
        return newCode;
    }

    @DELETE
    @Produces({MediaType.TEXT_PLAIN})
    @Path("{code}")
    public String deleteCard(@PathParam("code") String code) {
        // OK if deleted, ERR if it wasn't found and couldn't therefore be deleted
        return "OK";
    }

}
