package by.tms.d_project.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "forms_ots")
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
    //    @ManyToOne
//    @JsonIgnoreProperties("formsOts")
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id", nullable = false)
//    @JsonBackReference
    private Ots owner;
}