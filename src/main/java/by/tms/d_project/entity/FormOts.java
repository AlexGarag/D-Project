package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность хранит ту часть Разового рещения, что касающется непосредственно печатных форм
 */
@Entity
@Getter
@Setter
@Table(name = "forms_ots")
public class FormOts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_on_shaft")
    private int numberOnShaft;
    @Column(name = "title_form")
    private String titleForm;
    @Column(name = "indentation_on_shaft")
    private int indentationOnShaft;
    @Column(name = "interval_labels")
    private int intervalLabels;
    @Column(name = "tooth_on_shaft")
    private int toothOnShaft;
    @ManyToOne
    private Ots owner;
}