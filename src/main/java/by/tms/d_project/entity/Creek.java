package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "creeks")
public class Creek {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    private String title;
    private int width;
}