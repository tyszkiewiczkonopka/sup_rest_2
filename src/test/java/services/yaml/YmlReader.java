package services.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import models.config.Configuration;
import models.config.StudentConfiguration;
import models.webAPI.Student;

import java.io.File;
import java.io.IOException;

public class YmlReader {
    private static final String NEW_STUDENT = "src/test/resources/newStudent.yml";

    public Student getStudentFromConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Configuration config = mapper.readValue(new File(NEW_STUDENT), Configuration.class);

        StudentConfiguration newStudent = config.getNewStudent();

        return Student.builder()
                .firstName(newStudent.getFirstName())
                .middleName(newStudent.getMiddleName())
                .lastName(newStudent.getLastName())
                .dateOfBirth(newStudent.getDateOfBirth())
                .build();

    }
}
