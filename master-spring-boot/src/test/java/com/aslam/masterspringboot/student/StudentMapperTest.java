package com.aslam.masterspringboot.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void beforeEach() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudentClass() {
        StudentDto dto = new StudentDto(
                "Aslam",
                "Shaik",
                "aslam_shaik@epam.com",
                1);

        Student student = mapper.toStudent(dto);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        var msg = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("The Student Dto should not be null", msg.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDtoClass() {
        // Given
        Student student = new Student(
                "Areef",
                "Shaik",
                "areef_shaik@epam.com",
                32);

        // When
        StudentResponseDto studentResponseDto = mapper.toStudentResponseDto(student);

        // Then
        assertEquals(student.getFirstname(), studentResponseDto.firstname());
        assertEquals(student.getLastname(), studentResponseDto.lastname());
        assertEquals(student.getEmail(), studentResponseDto.email());
    }

}