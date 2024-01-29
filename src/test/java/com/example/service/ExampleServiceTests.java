package com.example.service;

import com.example.model.ExampleModel;
import com.example.repository.ExampleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExampleServiceTests {

    @Mock
    private ExampleRepository exampleRepository;

    @InjectMocks
    private ExampleService exampleService;

    private ExampleModel exampleModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        exampleModel = new ExampleModel(1L, "Test Name", "Test Description");
    }

    @Test
    public void testFindAll() {
        when(exampleRepository.findAll()).thenReturn(Arrays.asList(exampleModel));
        List<ExampleModel> result = exampleService.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(exampleModel, result.get(0));
    }

    @Test
    public void testFindById() {
        when(exampleRepository.findById(1L)).thenReturn(Optional.of(exampleModel));
        Optional<ExampleModel> result = exampleService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals(exampleModel, result.get());
    }

    @Test
    public void testSave() {
        when(exampleRepository.save(any(ExampleModel.class))).thenReturn(exampleModel);
        ExampleModel result = exampleService.save(new ExampleModel());
        assertNotNull(result);
        assertEquals(exampleModel, result);
    }

    @Test
    public void testUpdate() {
        when(exampleRepository.findById(anyLong())).thenReturn(Optional.of(exampleModel));
        when(exampleRepository.save(any(ExampleModel.class))).thenReturn(exampleModel);
        Optional<ExampleModel> result = exampleService.update(1L, new ExampleModel(1L, "Updated Name", "Updated Description"));
        assertTrue(result.isPresent());
        assertEquals("Updated Name", result.get().getName());
        assertEquals("Updated Description", result.get().getDescription());
    }

    @Test
    public void testDeleteById() {
        when(exampleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(exampleRepository).deleteById(1L);
        boolean result = exampleService.deleteById(1L);
        assertTrue(result);
        verify(exampleRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(exampleRepository.existsById(anyLong())).thenReturn(false);
        boolean result = exampleService.deleteById(1L);
        assertFalse(result);
        verify(exampleRepository, never()).deleteById(anyLong());
    }
}
