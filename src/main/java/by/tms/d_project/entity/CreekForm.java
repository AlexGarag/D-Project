package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "creek_form")
public class CreekForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "id_creek", nullable = false)
    private String idCreek;
    @Column(name = "id_form", nullable = false)
    private String idForm;
}