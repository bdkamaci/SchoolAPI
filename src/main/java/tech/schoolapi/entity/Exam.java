package tech.schoolapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.schoolapi.entity.enums.ExamType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exams")
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(name = "exam_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "exam_type", nullable = false)
    private ExamType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Grade> grades;

}
