package example.service;

import entity.Person;
import exception.Ok;
import exception.RecordNotFoundError;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Ines Heni
 *
 */
@Path("/person")
@OpenAPIDefinition(tags = @Tag(name = "personTag", description = "Person implementation."))
public class PersonServiceImpl implements PersonServiceIntf {

    private static List<Person> personList = Arrays.asList(new Person("Ines", "Heni", 25)
            , new Person("Sami", "Heni", 30));

    @GET
    @Path("/{firstName}/{lastName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = "personTag",
            operationId = "getPersonByFirstAndLastNameId",
            summary = "Retrives Person by first and last name."
            , responses = {
                    @ApiResponse(
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)), mediaType = MediaType.APPLICATION_JSON)
                            , description = "Person"
                            , responseCode = "200")
            , @ApiResponse(
                    content = @Content(schema = @Schema(implementation = RecordNotFoundError.class), mediaType = MediaType.APPLICATION_JSON)
                    , description = "Person not found"
                    , responseCode = "404")
    })
    @Override
    public Response getPersonByFirstAndLastName(
            @Parameter(description = "First Name", required = true) @PathParam("firstName") String firstName
            , @Parameter(description = "Last Name", required = true) @PathParam("lastName") String lastName) {
        Optional<Person> person = personList.stream().filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)).findFirst();
        if (!person.isPresent())
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        return Response.status(Response.Status.OK).entity(person.get()).type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{firstName}/{lastName}")
    @Operation(
            tags = "personTag",
            operationId = "deleteById",
            summary = "Delete Person by first and last name."
            , responses = {
            @ApiResponse(
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Ok.class)), mediaType = MediaType.APPLICATION_JSON)
                , description = "Person"
                , responseCode = "200")
            , @ApiResponse(
                content = @Content(schema = @Schema(implementation = RecordNotFoundError.class), mediaType = MediaType.APPLICATION_JSON)
                , description = "Person not found"
                , responseCode = "404")
    })
    @Override
    public void deleteById (@Parameter(description = "First Name", required = true) @PathParam("firstName") String firstName
            , @Parameter(description = "Last Name", required = true) @PathParam("lastName") String lastName) {
        Optional<Person> person = personList.stream().filter(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)).findFirst();
        if (person.isPresent())
            personList.remove(person);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Path("/average-age")
    @Operation(
            tags = "personTag",
            operationId = "averageAgeId",
            summary = "Compute the average age of the given list of Persons."
            , requestBody = @RequestBody(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Person.class)))))
    @Hidden
    public static Double averageAge(List<Person> persons) {
        return persons.stream()
                .mapToInt(Person::getAge)
                .average().getAsDouble();

    }
}
