package tech.schoolapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.schoolapi.entity.enums.EnrollmentStatus;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate date;

    @Column(name = "enrollment_status", nullable = false)
    private EnrollmentStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enrollment_student_id", referencedColumnName = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enrollment_course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;
}
