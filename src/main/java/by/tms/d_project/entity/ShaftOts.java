package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "shaft_ots")
public class ShaftOts { // Ots - one-time solution
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Column(name = "type_shaft", nullable = false)
    private int typeShaft;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    private List<FormOts> formsOts = new ArrayList<>();
    @ManyToOne
    private Account creator;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}