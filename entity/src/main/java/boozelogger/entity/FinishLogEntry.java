package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:56 AM
 */
@Entity
@Table(name = "finish_log_entry")
@XmlRootElement
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonTypeName("FinishLogEntry")
public class FinishLogEntry extends LogEntry {

    private String flavor;
    private String color;

    public FinishLogEntry() {
        this(null, null, null, null, null, null);
    }

    public FinishLogEntry(Integer id, Double temperature, String notes, Date createdAt, String flavor, String color) {
        super(id, temperature, notes, createdAt);
        this.flavor = flavor;
        this.color = color;
    }

    @Column(name="flavor")
    @JsonProperty
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Column(name="color")
    @JsonProperty
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
