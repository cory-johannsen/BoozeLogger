package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/1/14
 * Time: 8:02 AM
 */
@Entity
@Table(name="process")
public class Process {

    private Long id;
    private String name;
    private List<ProcessStep> steps;
    private Date createdAt;

    public Process() {
        this(null, null, new ArrayList<ProcessStep>(), null);
    }

    public Process(Long id, String name, List<ProcessStep> steps, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.steps = steps;
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

    @Column(name="name")
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "process_id")
    @JsonProperty
    public List<ProcessStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ProcessStep> steps) {
        this.steps = steps;
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
