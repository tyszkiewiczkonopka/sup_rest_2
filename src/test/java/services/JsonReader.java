package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.config.Configuration;
import models.config.StudentConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {
    private static final String NEW_STUDENTS = "src/test/resources/newStudents.json";

    public List<StudentConfiguration> getStudentsFromConfig() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Configuration config = mapper.readValue(new File(NEW_STUDENTS), Configuration.class);
        return config.getStudents();
    }
}
