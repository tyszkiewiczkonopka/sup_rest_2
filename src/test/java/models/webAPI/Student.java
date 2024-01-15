package models.webAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Student {

    private Integer id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;


    public Student() {
    }

    public Student(Integer id, String firstName, String middleName, String lastName, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student info: " + firstName + " " + middleName + " " + lastName + ". Date of birth: " + dateOfBirth + "\n" +
                "Student ID: " + id;
    }
}
