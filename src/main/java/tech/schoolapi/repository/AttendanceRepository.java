package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
