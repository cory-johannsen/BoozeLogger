package boozelogger.api;

import boozelogger.entity.Vessel;
import boozelogger.entity.dao.IVesselDao;
import com.google.inject.Inject;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import unification.configuration.Log;
import unification.entity.dao.exception.DaoException;
import unification.entity.dao.exception.EntityNotFoundException;
import unification.security.RolesBanned;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 1:01 PM
 */
@Path("/{api_version}/vessel")
public class VesselResource {

    @Log
    Logger logger;

    private IVesselDao vesselDao;

    @Inject
    public VesselResource(IVesselDao vesselDao) {
        this.vesselDao = vesselDao;
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public List<Vessel> getAll(@PathParam("api_version") String apiVersion)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Vessel list.");
        return vesselDao.loadAll();
    }

    @GET
    @Path("/{vessel_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Vessel getById(@PathParam("api_version") String apiVersion,
                          @PathParam("vessel_id") Integer vesselId)
            throws EntityNotFoundException, DaoException {
        logger.info("GET request (API version " + apiVersion + ") for Vessel with ID " + vesselId);
        return vesselDao.loadById(vesselId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Vessel create(@PathParam("api_version") String apiVersion,
                         Vessel vessel)
            throws DaoException {
        logger.info("POST request (API version " + apiVersion + ") to create Vessel " + vessel);
        return vesselDao.create(vessel);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Vessel update(@PathParam("api_version") String apiVersion,
                         Vessel vessel)
            throws DaoException {
        logger.info("PUT request (API version " + apiVersion + ") to update Vessel " + vessel);
        return vesselDao.store(vessel);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    @RolesAllowed({"administrator", "developer", "api_user"})
    @RolesBanned({"blacklist"})
    public Vessel remove(@PathParam("api_version") String apiVersion,
                         Vessel vessel)
            throws DaoException {
        logger.info("DELETE request (API version " + apiVersion + ") to remove Vessel " + vessel);
        return vesselDao.remove(vessel);
    }
}
