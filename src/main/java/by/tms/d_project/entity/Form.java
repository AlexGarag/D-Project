package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

//import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "forms")
public class Form {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, nullable = false)
    private String id;
    @Column(name = "quantity_imprint")
    private int quantityImprint;
    private int width;
    private int height;
    private String orientation;
    @Column(name = "right_margin")
    private int rightMargin;
    @Column(name = "interval_labels")
    private int intervalLabels;
}