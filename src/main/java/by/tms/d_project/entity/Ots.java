package by.tms.d_project.entity;

//import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(name = "shaftType", nullable = false)
    private int shaftType;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true/*, fetch = FetchType.LAZY*/)
//    @JsonManagedReference
    private List<FormOts> formsOts = new ArrayList<>();
    @ManyToOne
    private Account author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}