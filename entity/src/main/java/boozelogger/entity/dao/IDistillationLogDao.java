package boozelogger.entity.dao;

import boozelogger.entity.DistillationLog;
import unification.entity.dao.DataAccessObject;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 1:09 PM
 */
public interface IDistillationLogDao extends DataAccessObject<Integer, DistillationLog> {

    List<DistillationLog> loadByParameters(Map map) throws EntityNotFoundException, DaoException;

}
