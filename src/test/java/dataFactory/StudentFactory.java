package dataFactory;

import models.config.StudentConfiguration;
import models.webAPI.Student;
import services.yaml.YmlReader;

import java.io.IOException;

public class StudentFactory {
    public static Student createNewStudent() throws IOException {
        StudentConfiguration newStudentFromConfig = YmlReader.getStudentConfig();
        return Student.builder()
                .firstName(newStudentFromConfig.getFirstName())
                .middleName(newStudentFromConfig.getMiddleName())
                .lastName(newStudentFromConfig.getLastName())
                .dateOfBirth(newStudentFromConfig.getDateOfBirth())
                .build();
    }
}
