package boozelogger.entity.dao;

import boozelogger.entity.FermentLog;
import boozelogger.entity.FermentLogEntry;
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
public interface IFermentLogEntryDao extends DataAccessObject<Integer, FermentLogEntry> {

    List<FermentLogEntry> loadByParameters(Map map) throws EntityNotFoundException, DaoException;

}
