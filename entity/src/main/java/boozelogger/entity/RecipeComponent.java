package boozelogger.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:53 AM
 */
@Entity
@Table(name="recipe_component")
public class RecipeComponent {

    private Long id;
    private Ingredient ingredient;
    private Double amount;
    private UnitOfMeasurement unit;
    private Date createdAt;

    public RecipeComponent() {
        this(null, null, null, null, null);
    }

    public RecipeComponent(Long id, Ingredient ingredient, Double amount,
                           UnitOfMeasurement unit, Date createdAt) {
        this.id = id;
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
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
    @JoinColumn(name="ingredient_id")
    @JsonProperty
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Column(name="amount")
    @JsonProperty
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
