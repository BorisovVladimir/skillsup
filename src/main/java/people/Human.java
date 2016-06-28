package people;

import annotation.CustomDateFormat;
import annotation.JsonValue;

import java.time.LocalDate;

/**
 * Created by set on 28.06.2016.
 */
public class Human {
    private String firstName;
    private String lastName;
    @JsonValue(name="fun")
    private String hobby;
    @CustomDateFormat(format="dd-MM-yyyy")
    private LocalDate birthDate;


    public Human(String firstName, String lastName, String hobby, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobby = hobby;
        this.birthDate = birthDate;
    }
}
