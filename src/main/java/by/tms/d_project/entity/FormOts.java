package by.tms.d_project.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "forms_ots")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FormOts {  // todo добавить зуб
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
    @JoinColumn(name = "owner_id")
//    @JsonManagedReference
    private Ots owner;

    @JsonIgnore
    public Ots getOwner() {
        return owner;
    }
}