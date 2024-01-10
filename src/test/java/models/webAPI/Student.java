package models.webAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class Student {
    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;


    public Student() {
    }

    @Override
    public String toString() {
        return "Student info: " + first_name + " " + middle_name + " " + last_name + ". Date of birth: " + date_of_birth + "\n" + "Student ID: " + id;
    }
}
