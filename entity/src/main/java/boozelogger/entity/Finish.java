package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:56 AM
 */
@Entity
@Table(name = "finish")
public class Finish {

    private Long id;
    private Ferment ferment;
    private Distillation distillation;
    private Double volume;
    private UnitOfMeasurement unit;
    private List<Vessel> vessels;
    private Date createdAt;

    public Finish() {
        this(null, null, null, null, null, new ArrayList<Vessel>(), null);
    }

    public Finish(Long id, Ferment ferment, Distillation distillation, Double volume, UnitOfMeasurement unit, List<Vessel> vessels, Date createdAt) {
        this.id = id;
        this.ferment = ferment;
        this.distillation = distillation;
        this.volume = volume;
        this.unit = unit;
        this.vessels = vessels;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name="ferment_id")
    @JsonProperty
    public Ferment getFerment() {
        return ferment;
    }

    public void setFerment(Ferment ferment) {
        this.ferment = ferment;
    }

    @OneToOne
    @JoinColumn(name="distillation_id")
    @JsonProperty
    public Distillation getDistillation() {
        return distillation;
    }

    public void setDistillation(Distillation distillation) {
        this.distillation = distillation;
    }

    @OneToMany
    @JoinTable(
            name="finish_vessel",
            joinColumns={ @JoinColumn(name="finish_id", referencedColumnName="id") },
            inverseJoinColumns={ @JoinColumn(name="vessel_id", referencedColumnName="id") }
    )
    @JsonProperty
    public List<Vessel> getVessels() {
        return vessels;
    }

    public void setVessels(List<Vessel> vessels) {
        this.vessels = vessels;
    }

    @Column(name="volume", columnDefinition = "numeric")
    @JsonProperty
    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="unit")
    @JsonProperty
    public UnitOfMeasurement getUnit() {
        return unit;
    }

    public void setUnit(UnitOfMeasurement unit) {
        this.unit = unit;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    @JsonProperty
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
