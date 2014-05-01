package boozelogger;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 4/30/14
 * Time: 10:56 AM
 */
@Entity
@Table(name = "finish")
public class Finish {

    private Long id;
    private Ferment ferment;
    private Distillation mDistillation;
    private List<Vessel> vessels;
    private Date createdAt;
}
