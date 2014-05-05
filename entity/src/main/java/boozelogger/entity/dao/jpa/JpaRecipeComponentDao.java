package boozelogger.entity.dao.jpa;

import boozelogger.entity.Recipe;
import boozelogger.entity.RecipeComponent;
import boozelogger.entity.dao.IRecipeComponentDao;
import boozelogger.entity.dao.IRecipeDao;
import unification.configuration.Log;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;
import unification.entity.dao.jpa.JpaDataAccessObject;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 1:35 PM
 */
public class JpaRecipeComponentDao extends JpaDataAccessObject<Integer,RecipeComponent>
implements IRecipeComponentDao {
    @Log
    org.slf4j.Logger mLogger;

    /**
     *
     */
    @Inject
    public JpaRecipeComponentDao(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    public List<RecipeComponent> loadByParameters(Map map) throws EntityNotFoundException, DaoException {
        return loadAll();
    }
}
