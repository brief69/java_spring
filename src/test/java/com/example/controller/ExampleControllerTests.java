package com.example.controller;

import com.example.model.ExampleModel;
import com.example.service.ExampleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class ExampleControllerTests {

    @Mock
    private ExampleService exampleService;

    @InjectMocks
    private ExampleController exampleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllExamples_ShouldReturnAllExamples() {
        // Arrange
        List<ExampleModel> expectedExamples = Arrays.asList(
                new ExampleModel(1L, "Example1", "Description1"),
                new ExampleModel(2L, "Example2", "Description2")
        );
        when(exampleService.findAll()).thenReturn(expectedExamples);

        // Act
        ResponseEntity<?> response = exampleController.getAllExamples();

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(expectedExamples));
    }

    @Test
    void getExampleById_WhenFound_ShouldReturnExample() {
        // Arrange
        Long exampleId = 1L;
        ExampleModel expectedExample = new ExampleModel(exampleId, "Example1", "Description1");
        when(exampleService.findById(exampleId)).thenReturn(Optional.of(expectedExample));

        // Act
        ResponseEntity<?> response = exampleController.getExampleById(exampleId);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(expectedExample));
    }

    @Test
    void getExampleById_WhenNotFound_ShouldReturnNotFound() {
        // Arrange
        Long exampleId = 1L;
        when(exampleService.findById(exampleId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = exampleController.getExampleById(exampleId);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    void createExample_ShouldReturnSavedExample() {
        // Arrange
        ExampleModel exampleToSave = new ExampleModel(null, "Example1", "Description1");
        ExampleModel savedExample = new ExampleModel(1L, "Example1", "Description1");
        when(exampleService.save(exampleToSave)).thenReturn(savedExample);

        // Act
        ResponseEntity<ExampleModel> response = exampleController.createExample(exampleToSave);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(savedExample));
    }

    @Test
    void updateExample_WhenFound_ShouldReturnUpdatedExample() {
        // Arrange
        Long exampleId = 1L;
        ExampleModel updateInfo = new ExampleModel(exampleId, "UpdatedExample", "UpdatedDescription");
        when(exampleService.update(exampleId, updateInfo)).thenReturn(Optional.of(updateInfo));

        // Act
        ResponseEntity<?> response = exampleController.updateExample(exampleId, updateInfo);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo(updateInfo));
    }

    @Test
    void updateExample_WhenNotFound_ShouldReturnNotFound() {
        // Arrange
        Long exampleId = 1L;
        ExampleModel updateInfo = new ExampleModel(exampleId, "UpdatedExample", "UpdatedDescription");
        when(exampleService.update(exampleId, updateInfo)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = exampleController.updateExample(exampleId, updateInfo);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    void deleteExample_WhenFound_ShouldReturnOk() {
        // Arrange
        Long exampleId = 1L;
        when(exampleService.deleteById(exampleId)).thenReturn(true);

        // Act
        ResponseEntity<?> response = exampleController.deleteExample(exampleId);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    void deleteExample_WhenNotFound_ShouldReturnNotFound() {
        // Arrange
        Long exampleId = 1L;
        when(exampleService.deleteById(exampleId)).thenReturn(false);

        // Act
        ResponseEntity<?> response = exampleController.deleteExample(exampleId);

        // Assert
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}
