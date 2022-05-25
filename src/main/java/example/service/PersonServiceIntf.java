package example.service;

import entity.Person;

import javax.ws.rs.core.Response;

/**
 * @author Ines Heni
 *
 */
public interface PersonServiceIntf {

    /**
     * Retrive person by first and last name
     *
     * @param firstName
     * @param lastName
     * @return {@link Person}
     */
    Response getPersonByFirstAndLastName(String firstName, String lastName);

    /**
     * Delete person by first and last name
     *
     * @param firstName
     * @param lastName
     */
    void deleteById (String firstName, String lastName);
}
