package by.tms.d_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import static by.tms.d_project.constant_reference_etc.Constant.*;

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
    @Min(value = MIN_WIDTH)
    @Max(value = MAX_WIDTH)
    @Pattern(regexp = "^\\d+$")
    private int width;
    @Min(value = MIN_MARGIN)
    @Max(value = MAX_WIDTH)
    @Pattern(regexp = "^\\d+$")
    @Column(name = "right_margin")
    private int rightMargin;
    @Min(value = MIN_INTERVAL)
    @Max(value = MAX_WIDTH)
    @Pattern(regexp = "^\\d+$")
    @Column(name = "interval_labels")
    private int intervalLabels;
    @ManyToOne(fetch = FetchType.LAZY)
    private IcOts owner;
}