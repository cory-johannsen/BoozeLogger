package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:50 AM
 */
@Entity
@Table(name="recipe")
public class Recipe {

    private Integer id;
    private String name;
    private RecipeType type;
    private Date createdAt;
    private List<RecipeComponent> components;
    private Process process;

    public Recipe() {
        this(null, null, null, null, new ArrayList<RecipeComponent>(), null);
    }

    public Recipe(Integer id, String name, RecipeType type, Date createdAt, List<RecipeComponent> components, Process process) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.components = components;
        this.process = process;
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

    @Column(name="name")
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    @JsonProperty
    public List<RecipeComponent> getComponents() {
        return components;
    }

    public void setComponents(List<RecipeComponent> components) {
        this.components = components;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    @JsonProperty
    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "process_id")
    @JsonProperty
    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
