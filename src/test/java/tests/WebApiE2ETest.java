package tests;

import lombok.extern.slf4j.Slf4j;
import models.webAPI.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static dataFactory.StudentFactory.createNewStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static services.api.RestAssuredHelper.*;

@Slf4j
public class WebApiE2ETest {

    @Test
    public void should_create_update_and_delete_student() throws IOException {
        Student newStudent = createNewStudent();
        // POST
        Integer createdStudentID = postNewStudent(newStudent);
        // GET
        getStudentDetailsByID(createdStudentID);
        // PUT
        Student studentWithUpdatedMiddleName = updateStudentMiddleName(newStudent, createdStudentID, "Tyszkiewicz");
        // GET
        Student studentFromDeserializedResponse = deserializeUpdatedStudentFromResponse(createdStudentID);
        assertThat(studentFromDeserializedResponse).usingRecursiveComparison().isEqualTo(studentWithUpdatedMiddleName);
        // DELETE
        deleteStudent(createdStudentID);
        // GET
        getStudentAfterDelete(createdStudentID);
    }

}

