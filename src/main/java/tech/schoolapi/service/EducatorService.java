package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Educator;

public interface EducatorService {

    public ResponseEntity addEducator(Educator educator);

    public ResponseEntity updateEducator(Educator educator);

    public ResponseEntity deleteEducator(Long id);

    public ResponseEntity findEducator(Long id);

    public ResponseEntity findAllEducators();
}
