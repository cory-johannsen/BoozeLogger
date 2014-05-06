package boozelogger.entity.dao.jpa;

import boozelogger.entity.FermentLog;
import boozelogger.entity.FermentLogEntry;
import boozelogger.entity.dao.IFermentLogDao;
import boozelogger.entity.dao.IFermentLogEntryDao;
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
public class JpaFermentLogEntryDao extends JpaDataAccessObject<Integer,FermentLogEntry> implements IFermentLogEntryDao {
    @Log
    org.slf4j.Logger mLogger;

    /**
     *
     */
    @Inject
    public JpaFermentLogEntryDao(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
    }

    @Override
    public List<FermentLogEntry> loadByParameters(Map map) throws EntityNotFoundException, DaoException {
        return loadAll();
    }
}
