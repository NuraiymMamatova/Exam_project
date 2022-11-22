package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "group_seq",sequenceName = "group_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private Date dateOfStart;

    private String image;

//    @ManyToMany(cascade = {REFRESH, MERGE, DETACH}, fetch = FetchType.LAZY)
//    @JoinTable(name = "groups_courses",
//    joinColumns = @JoinColumn(name = "group_id"),
//    inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private List<Course> courses;
//
//    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "group")
//    private List<Student> students;

    public Group(String groupName, Date dateOfStart, String image) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.image = image;
    }
}
