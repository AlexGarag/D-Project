package by.tms.d_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@ToString
@Table(name = "shafts_ic_ots")
public class ShaftIcOts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Column(name = "type_shaft", nullable = false)
    private int typeShaft;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    private List<FormIcOts> formsIcOts = new ArrayList<>();

    @ManyToOne
    private Account creator;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}