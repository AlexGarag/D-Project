package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "creeks")
public class Creek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "unique_name", nullable = false)
    private String uniqName;
    private String title;
    @Column(name = "quantity_imprint")
    private int quantityImprint;
    private int width;
    private int height;
    @Column(name = "right_margin")
    private int rightMargin;
    @Column(name = "interval_labels")
    private int intervalLabels;
}