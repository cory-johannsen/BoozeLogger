package boozelogger.entity.dao;

import boozelogger.entity.FermentLog;
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
public interface IFermentLogDao extends DataAccessObject<Integer, FermentLog> {

    List<FermentLog> loadByParameters(Map map) throws EntityNotFoundException, DaoException;

}
