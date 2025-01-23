package by.tms.d_project.entity;

//import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "ic_ots")
public class IcOts { // Ic - initial conditions + Ots - one-time solution
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Column(name = "type_shaft", nullable = false)
    private int typeShaft;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY*/)
//    @JsonManagedReference
    private List<FormIcOts> formsIcOts = new ArrayList<>();
    @ManyToOne
    private Account author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}