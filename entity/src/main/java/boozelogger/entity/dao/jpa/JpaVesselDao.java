package boozelogger.entity.dao.jpa;

import boozelogger.entity.Vessel;
import boozelogger.entity.dao.IVesselDao;
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
public class JpaVesselDao extends JpaDataAccessObject<Integer,Vessel> implements IVesselDao {
    @Log
    org.slf4j.Logger mLogger;

    /**
     *
     */
    @Inject
    public JpaVesselDao(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    public List<Vessel> loadByParameters(Map map) throws EntityNotFoundException, DaoException {
        return loadAll();
    }
}
