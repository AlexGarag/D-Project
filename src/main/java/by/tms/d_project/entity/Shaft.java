package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "shafts")
public class Shaft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "id_printing", nullable = false)
    private String idPrinting;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int type;
}