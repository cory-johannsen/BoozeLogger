package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:55 AM
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FermentLogEntry.class, name = "FermentLogEntry"),
        @JsonSubTypes.Type(value = DistillationLogEntry.class, name = "DistillationLogEntry")
})
public abstract class LogEntry {

    private Long id;
    private Double temperature;
    private String notes;
    private Date createdAt;

    protected LogEntry() {
        this(null, null, null, null);
    }

    protected LogEntry(Long id, Double temperature, String notes, Date createdAt) {
        this.id = id;
        this.temperature = temperature;
        this.notes = notes;
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


    @Column(name="temperature", columnDefinition = "numeric")
    @JsonProperty
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Column(name="notes")
    @JsonProperty
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
