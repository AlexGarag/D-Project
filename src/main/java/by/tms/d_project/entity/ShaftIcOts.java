package by.tms.d_project.entity;

import by.tms.d_project.dto.AccountDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "shaft_ic_ots")
public class ShaftIcOts { // Ic - initial conditions, Ots - one-time solution
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title_printing", nullable = false)
    private String titlePrinting;
    @Column(name = "type_shaft", nullable = false)
    private int typeShaft;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER/*, orphanRemoval = true*/)
    private List<FormIcOts> formsIcOts = new ArrayList<>();
    @ManyToOne
    private Account creator;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}