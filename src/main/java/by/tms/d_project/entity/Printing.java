package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "printings")
public class Printing {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    @Column(nullable = false)
    private String title;
}