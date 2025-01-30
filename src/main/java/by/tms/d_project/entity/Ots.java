package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Сущность хранит сформированное разовое решение (OTS - one-time solution)
 */
@Entity
@Getter
@Setter
@Table(name = "ots")
public class Ots { // Ots - one-time solution
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Column(name = "shaft_size", nullable = false)
    private int shaftSize;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormOts> formsOts = new ArrayList<>();
    @ManyToOne
    private Account author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}