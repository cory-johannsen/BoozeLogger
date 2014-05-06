package boozelogger.entity.dao;

import boozelogger.entity.Finish;
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
public interface IFinishDao extends DataAccessObject<Integer, Finish> {

    List<Finish> loadByParameters(Map map) throws EntityNotFoundException, DaoException;

}
