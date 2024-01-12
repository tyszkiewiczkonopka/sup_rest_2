package models.config;

import lombok.Getter;

@Getter
public class StudentConfiguration {

    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;

    @Override
    public String toString() {
        return "Student info: " + firstName + " " + middleName + " " + lastName + ". Date of birth: " + dateOfBirth;
    }
}
