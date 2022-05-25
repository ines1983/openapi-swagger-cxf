package entity;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Ines Heni
 *
 */

@Schema(description = "Person data")
@XmlRootElement(name = "Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(defaultValue = "Ines", description = "First Name")
    private String firstName;

    @Schema(defaultValue = "Heni", description = "Last Name")
    private String lastName;

    @Schema(defaultValue = "25", description = "Age")
    private Integer age;

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
