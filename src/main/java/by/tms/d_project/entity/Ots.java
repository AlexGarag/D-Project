package by.tms.d_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ots")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ots { // Ots - one-time solution
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Column(name = "shaftType", nullable = false)
    private int shaftType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
//    @JsonBackReference
    private List<FormOts> formsOts = new ArrayList<>();
    @ManyToOne
    private Account author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}