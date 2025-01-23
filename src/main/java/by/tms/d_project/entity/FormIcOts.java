package by.tms.d_project.entity;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "forms_ic_ots")
public class FormIcOts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_form")
    private String titleForm;
    @Column(name = "quantity_imprint")
    private int quantityImprint;
    private int width;
    @Column(name = "right_margin")
    private int rightMargin;
    @Column(name = "interval_labels")
    private int intervalLabels;

    //    @ManyToOne
//    @JsonIgnoreProperties("formsIcOts")
    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id", nullable = false)
//    @JsonBackReference
    private IcOts owner;
}