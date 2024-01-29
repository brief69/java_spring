package com.example.controller;

import com.example.model.ExampleModel;
import com.example.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examples")
public class ExampleController {

    private final ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllExamples() {
        return ResponseEntity.ok(exampleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExampleById(@PathVariable Long id) {
        return exampleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExampleModel> createExample(@RequestBody ExampleModel exampleModel) {
        ExampleModel savedExample = exampleService.save(exampleModel);
        return ResponseEntity.ok(savedExample);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExample(@PathVariable Long id, @RequestBody ExampleModel exampleModel) {
        return exampleService.update(id, exampleModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Long id) {
        if (exampleService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
