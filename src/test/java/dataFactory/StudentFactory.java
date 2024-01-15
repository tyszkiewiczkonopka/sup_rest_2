package dataFactory;

import models.webAPI.Student;
import services.yaml.YmlReader;

import java.io.IOException;

public class StudentFactory {
    public static Student createNewStudent() throws IOException {
        return new YmlReader().getStudentFromConfig();
    }
}
