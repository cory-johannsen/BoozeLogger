package boozelogger.entity.dao.jpa;

import boozelogger.entity.Finish;
import boozelogger.entity.dao.IFinishDao;
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
public class JpaFinishDao extends JpaDataAccessObject<Integer,Finish> implements IFinishDao {
    @Log
    org.slf4j.Logger mLogger;

    /**
     *
     */
    @Inject
    public JpaFinishDao(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    public List<Finish> loadByParameters(Map map) throws EntityNotFoundException, DaoException {
        return loadAll();
    }
}
