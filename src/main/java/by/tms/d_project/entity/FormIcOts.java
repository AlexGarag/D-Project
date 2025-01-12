package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "forms_ic_ots")
public class FormIcOts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String title;
    @Column(name = "quantity_imprint")
    private int quantityImprint;
    private int width;
    @Column(name = "right_margin")
    private int rightMargin;
    @Column(name = "interval_labels")
    private int intervalLabels;

    @ManyToOne
    private ShaftIcOts owner;
}
