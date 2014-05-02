package boozelogger.entity.dao;

import boozelogger.entity.Recipe;
import boozelogger.entity.dao.exception.DaoException;
import boozelogger.entity.dao.exception.EntityNotFoundException;
import com.google.inject.persist.Transactional;
import unification.configuration.Log;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 1:35 PM
 */
public class JpaRecipeDao extends DataAccessObject<Recipe> {
    @Log
    org.slf4j.Logger mLogger;
    private Provider<EntityManager> mEntityManagerProvider;

    /**
     *
     */
    @Inject
    public JpaRecipeDao(Provider<EntityManager> entityManagerProvider) {
        mEntityManagerProvider = entityManagerProvider;
    }


    @Override
    public Recipe loadById(Long id) throws EntityNotFoundException, DaoException {
        Recipe recipe = mEntityManagerProvider.get().find(Recipe.class, id);
        if (recipe == null) {
            throw new EntityNotFoundException("No " + Recipe.class + " found for id " + id);
        }
        return recipe;
    }

    @Override
    public List<Recipe> loadAll() throws EntityNotFoundException, DaoException {
        TypedQuery<Recipe> query = mEntityManagerProvider.get().createQuery("SELECT recipe FROM Recipe recipe",
                Recipe.class);
        try {
            List<Recipe> resultList = query.getResultList();
            return resultList;
        } catch (NoResultException ex) {
            throw new EntityNotFoundException("No " + Recipe.class + " found");
        }
    }

    @Override
    @Transactional
    public Recipe create(Recipe recipe) throws DaoException {
        mEntityManagerProvider.get().persist(recipe);
        return recipe;
    }

    @Override
    @Transactional
    public Recipe store(Recipe recipe) throws DaoException {
        return mEntityManagerProvider.get().merge(recipe);
    }

    @Override
    @Transactional
    public Recipe remove(Recipe recipe) throws DaoException {
        mEntityManagerProvider.get().remove(recipe);
        return recipe;
    }
}
