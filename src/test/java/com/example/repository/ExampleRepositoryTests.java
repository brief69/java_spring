package com.example.repository;

import com.example.model.ExampleModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExampleRepositoryTests {

    @Autowired
    private ExampleRepository exampleRepository;

    private ExampleModel exampleModel;

    @BeforeEach
    public void setUp() {
        // Initialize your model with test data
        exampleModel = new ExampleModel();
        exampleModel.setName("Test Name");
        exampleModel.setDescription("Test Description");
        // Save the test data to the repository
        exampleRepository.save(exampleModel);
    }

    @Test
    public void whenFindById_thenReturnExampleModel() {
        // Given
        Long id = exampleModel.getId();

        // When
        Optional<ExampleModel> foundModel = exampleRepository.findById(id);

        // Then
        assertThat(foundModel).isPresent();
        assertThat(foundModel.get()).isEqualTo(exampleModel);
    }

    @Test
    public void whenFindAll_thenReturnListOfExampleModels() {
        // When
        Iterable<ExampleModel> models = exampleRepository.findAll();

        // Then
        assertThat(models).contains(exampleModel);
    }

    @Test
    public void whenSaveExampleModel_thenCorrectlySaved() {
        // Given
        ExampleModel newModel = new ExampleModel();
        newModel.setName("New Test Name");
        newModel.setDescription("New Test Description");

        // When
        ExampleModel savedModel = exampleRepository.save(newModel);

        // Then
        assertThat(savedModel).isNotNull();
        assertThat(savedModel.getId()).isNotNull();
        assertThat(savedModel.getName()).isEqualTo(newModel.getName());
        assertThat(savedModel.getDescription()).isEqualTo(newModel.getDescription());
    }

    @Test
    public void whenDeleteById_thenRemovedFromRepository() {
        // Given
        Long id = exampleModel.getId();

        // When
        exampleRepository.deleteById(id);
        Optional<ExampleModel> deletedModel = exampleRepository.findById(id);

        // Then
        assertThat(deletedModel).isNotPresent();
    }
}
