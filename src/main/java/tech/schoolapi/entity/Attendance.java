package tech.schoolapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.schoolapi.entity.enums.AttendanceType;

import java.time.LocalDate;

@Entity
@Table(name = "attendances")
@Getter
@Setter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Long id;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate date;

    @Column(name = "attendance_type", nullable = false)
    private AttendanceType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendance_course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendance_student_id", referencedColumnName = "student_id", nullable = false)
    private Student student;

}
