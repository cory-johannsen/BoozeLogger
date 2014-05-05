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
@Table(name="finish_log")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("FinishLog")
public class FinishLog extends Log {


    private List<FinishLogEntry> entries;

    public FinishLog() {
        this(null, null, null, null, new ArrayList<FinishLogEntry>());
    }

    public FinishLog(Integer id, String notes, Vessel vessel, Date createdAt, List<FinishLogEntry> entries) {
        super(id, notes, vessel, createdAt);
        this.entries = entries;
    }

    @OneToMany
    @JoinColumn(name="finish_log_id")
    public List<FinishLogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<FinishLogEntry> entries) {
        this.entries = entries;
    }
}
