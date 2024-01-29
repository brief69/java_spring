package com.example.service;

import com.example.model.ExampleModel;
import com.example.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;

    @Autowired
    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public List<ExampleModel> findAll() {
        return exampleRepository.findAll();
    }

    public Optional<ExampleModel> findById(Long id) {
        return exampleRepository.findById(id);
    }

    public ExampleModel save(ExampleModel exampleModel) {
        return exampleRepository.save(exampleModel);
    }

    public Optional<ExampleModel> update(Long id, ExampleModel exampleModel) {
        return exampleRepository.findById(id)
                .map(existingExample -> {
                    existingExample.setName(exampleModel.getName());
                    existingExample.setDescription(exampleModel.getDescription());
                    // Update other fields as necessary
                    return exampleRepository.save(existingExample);
                });
    }

    public boolean deleteById(Long id) {
        if (exampleRepository.existsById(id)) {
            exampleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
