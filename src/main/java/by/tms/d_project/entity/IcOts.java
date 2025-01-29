package by.tms.d_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static by.tms.d_project.constant_reference_etc.Constant.MAX_SHAFT_SIZE;
import static by.tms.d_project.constant_reference_etc.Constant.MIN_SHAFT_SIZE;

/**
 *  Сущность хранит начальные условия (IC - initial conditions) для выработки
 *  разового решения (OTS - one-time solution)
 */
@Entity
@Setter
@Getter
@Table(name = "ic_ots")
public class IcOts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Min(MIN_SHAFT_SIZE)
    @Max(MAX_SHAFT_SIZE)
    @Column(name = "shaft_size")
    private int shaftSize;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<FormIcOts> formsIcOts = new ArrayList<>();
    @ManyToOne
    private Account author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}