package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Educator;

public interface EducatorRepository extends JpaRepository<Educator, Long> {
}
