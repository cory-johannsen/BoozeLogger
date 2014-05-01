package boozelogger;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:54 AM
 */
@Entity
@Table(name="log")
public class Log {

    private Long id;
    private String notes;
    private Vessel vessel;
    private List<LogEntry> entries;
    private Date createdAt;

    public Log() {
        this(null, null, null, new ArrayList<LogEntry>(), null);
    }

    public Log(Long id, String notes, Vessel vessel, List<LogEntry> entries, Date createdAt) {
        this.id = id;
        this.notes = notes;
        this.vessel = vessel;
        this.entries = entries;
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

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "log_id")
    @JsonProperty
    public List<LogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<LogEntry> entries) {
        this.entries = entries;
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
