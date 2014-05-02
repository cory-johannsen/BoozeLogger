package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 8:34 AM
 */
@Entity
@Table(name="distillation_log")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("DistillationLog")
public class DistillationLog extends Log {


    private List<DistillationLogEntry> entries;

    public DistillationLog() {
        this(null, null, null, null, new ArrayList<DistillationLogEntry>());
    }

    public DistillationLog(Long id, String notes, Vessel vessel, Date createdAt, List<DistillationLogEntry> entries) {
        super(id, notes, vessel, createdAt);
        this.entries = entries;
    }

    @OneToMany
    @JoinColumn(name="distillation_log_id")
    public List<DistillationLogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<DistillationLogEntry> entries) {
        this.entries = entries;
    }
}
