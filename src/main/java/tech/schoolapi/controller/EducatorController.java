package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.educator.EducatorSaveRequest;
import tech.schoolapi.dto.request.educator.EducatorUpdateRequest;
import tech.schoolapi.entity.Educator;
import tech.schoolapi.service.EducatorService;

@RestController
@RequestMapping("/api/educators")
@RequiredArgsConstructor
public class EducatorController {
    private final EducatorService educatorService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return educatorService.findAllEducators();
    }

    @GetMapping("/{id}")
    public ResponseEntity findEducator(@PathVariable Long id) {
        return educatorService.findEducator(id);
    }

    @PostMapping()
    public ResponseEntity addEducator(@RequestBody EducatorSaveRequest educator) {
        return educatorService.addEducator(modelMapperService.forRequest().map(educator, Educator.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEducator(@RequestBody EducatorUpdateRequest educator) {
        return educatorService.updateEducator(modelMapperService.forRequest().map(educator, Educator.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEducator(@PathVariable Long id) {
        return educatorService.deleteEducator(id);
    }
}
