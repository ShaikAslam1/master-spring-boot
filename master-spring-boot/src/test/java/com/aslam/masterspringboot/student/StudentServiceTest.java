package com.aslam.masterspringboot.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    // Which service we want to test
    @InjectMocks
    private StudentService studentService;

    // declare the dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        // to open the mocks for this current class
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        // Given
        StudentDto dto = new StudentDto(
                "Afrose",
                "Shaik",
                "afrose_shaik@epam.com",
                136);
        Student student = new Student(
                "Afrose",
                "Shaik",
                "afrose_shaik@epam.com",
                36);
        Student savedStudent = new Student(
                "Afrose",
                "Shaik",
                "afrose_shaik@epam.com",
                36);
        savedStudent.setId(1);

        // Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "Afrose",
                        "Shaik",
                        "afrose_shaik@epam.com"));


        // When
        StudentResponseDto studentResponseDto = studentService.createStudent(dto);

        // Then
        assertEquals(dto.firstname(), studentResponseDto.firstname());
        assertEquals(dto.lastname(), studentResponseDto.lastname());
        assertEquals(dto.email(), studentResponseDto.email());

        // verify save called exactly once
        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Afrose",
                "Shaik",
                "afrose_shaik@epam.com",
                36));

        // Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Afrose", "Shaik", "afrose_shaik@epam.com"));

        // When
        Collection<StudentResponseDto> allStudents = studentService.getAllStudents();

        // Then
        assertEquals(students.size(), allStudents.size());

        // Verify
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        // Given
        Integer studentId = 2;
        Student student = new Student(
                "Javeriya",
                "Shaik",
                "javeriya_shaik@epam.com",
                24);
        student.setId(studentId);

        StudentResponseDto studentResponseDto = new StudentResponseDto(
                "Javeriya", "Shaik", "javeriya_shaik@epam.com");

        // Mock the calls
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(studentResponseDto);

        // When
        StudentResponseDto studentById = studentService.findStudentById(studentId);

        // Then
        assertEquals(studentById.firstname(), studentResponseDto.firstname());
        assertEquals(studentById.lastname(), studentResponseDto.lastname());
        assertEquals(studentById.email(), studentResponseDto.email());

        // Verify
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void should_return_student_by_name() {
        // Given
        String firstName = "Zeba";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Zeba",
                "Shaik",
                "Zeba_shaik@epam.com",
                5));

        // Mock the calls
        when(studentRepository.findByFirstnameContaining(firstName)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Zeba", "Shaik", "Zeba_shaik@epam.com"));

        // When
        Collection<StudentResponseDto> studentsByName = studentService.findStudentsByName(firstName);

        // Then
        assertEquals(students.size(), studentsByName.size());

        // verify
        verify(studentRepository, times(1)).findByFirstnameContaining(firstName);
    }
}