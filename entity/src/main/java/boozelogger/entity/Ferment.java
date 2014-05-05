package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:53 AM
 */
@Entity
@Table(name="ferment")
public class Ferment {

    private Integer id;
    private String name;
    private String description;
    private Recipe recipe;
    private List<Vessel> vessels;
    private Double originalGravity;
    private Double temperature;
    private Date startDate;
    private Date createdAt;

    public Ferment() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public Ferment(Integer id, String name, String description, Recipe recipe, List<Vessel> vessels,
                   Double originalGravity, Double temperature, Date startDate,
                   Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.vessels = vessels;
        this.originalGravity = originalGravity;
        this.temperature = temperature;
        this.startDate = startDate;
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

    @Column(name="name")
    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne
    @JoinColumn(name="recipe_id")
    @JsonProperty
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @OneToMany
    @JoinTable(
            name="ferment_vessel",
            joinColumns={ @JoinColumn(name="ferment_id", referencedColumnName="id") },
            inverseJoinColumns={ @JoinColumn(name="vessel_id", referencedColumnName="id") }
    )
    @JsonProperty
    public List<Vessel> getVessels() {
        return vessels;
    }

    public void setVessels(List<Vessel> vessels) {
        this.vessels = vessels;
    }

    @Column(name="original_gravity", columnDefinition = "numeric")
    @JsonProperty
    public Double getOriginalGravity() {
        return originalGravity;
    }

    public void setOriginalGravity(Double originalGravity) {
        this.originalGravity = originalGravity;
    }

    @Column(name="temperature", columnDefinition = "numeric")
    @JsonProperty
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date")
    @JsonProperty
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
