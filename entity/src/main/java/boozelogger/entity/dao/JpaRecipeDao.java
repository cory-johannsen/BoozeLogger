package boozelogger.entity.dao;

import boozelogger.entity.Recipe;
import unification.entity.dao.jpa.JpaDataAccessObject;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;
import com.google.inject.persist.Transactional;
import unification.configuration.Log;


import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 1:35 PM
 */
public class JpaRecipeDao extends JpaDataAccessObject<Long,Recipe> {
    @Log
    org.slf4j.Logger mLogger;

    /**
     *
     */
    @Inject
    public JpaRecipeDao(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    public List<Recipe> loadByParameters(Map map) throws EntityNotFoundException, DaoException {
        return loadAll();
    }
}
