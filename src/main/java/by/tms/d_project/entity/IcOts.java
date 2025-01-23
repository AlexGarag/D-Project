package by.tms.d_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @NotEmpty
    @NotNull
    @Column(name = "type_shaft", nullable = false)
    private int typeShaft;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<FormIcOts> formsIcOts = new ArrayList<>();
    @ManyToOne
    private Account author;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createdAt = new Date();
}