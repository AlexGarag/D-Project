package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "shaft_form")
public class ShaftForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "id_shaft", nullable = false)
    private UUID idShaft;
    private int number;
    @Column(name = "id_form", nullable = false)
    private String idForm;
}