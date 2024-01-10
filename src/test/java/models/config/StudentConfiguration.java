package models.config;

import lombok.Getter;

@Getter
public class StudentConfiguration {

    public String first_name;
    public String middle_name;
    public String last_name;
    public String date_of_birth;

    @Override
    public String toString() {
        return "Student info: " + first_name + " " + middle_name + " " + last_name + ". Date of birth: " + date_of_birth;
    }
}
