package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:54 AM
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FermentLog.class, name = "Type_10"),
        @JsonSubTypes.Type(value = DistillationLog.class, name = "Type_15"),
        @JsonSubTypes.Type(value = FinishLog.class, name = "Type_20")
})
public abstract class Log {

    private Integer id;
    private String notes;
    private Vessel vessel;
    private Date createdAt;

    public Log() {
        this(null, null, null, null);
    }

    public Log(Integer id, String notes, Vessel vessel, Date createdAt) {
        this.id = id;
        this.notes = notes;
        this.vessel = vessel;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="notes")
    @JsonProperty
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @OneToOne
    @JoinColumn(name="vessel_id")
    @JsonProperty
    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
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
