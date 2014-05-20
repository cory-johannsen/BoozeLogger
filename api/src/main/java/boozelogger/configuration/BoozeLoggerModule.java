package boozelogger.configuration;

import boozelogger.entity.dao.*;
import boozelogger.entity.dao.jpa.*;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unification.configuration.GrandUnificationModule;
import unification.exceptionmapper.DaoExceptionMapper;
import unification.exceptionmapper.EntityNotFoundExceptionMapper;
import unification.exceptionmapper.UnauthenticatedExceptionMapper;

import java.util.Map;
import java.util.Properties;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 10:14 AM
 */
public class BoozeLoggerModule extends GrandUnificationModule {

    private static final Logger log = LoggerFactory.getLogger(BoozeLoggerModule.class);

    @Override
    protected void bindApplicationInterfaces() {
        bind(IDistillationDao.class).to(JpaDistillationDao.class);
        bind(IDistillationLogDao.class).to(JpaDistillationLogDao.class);
        bind(IDistillationLogEntryDao.class).to(JpaDistillationLogEntryDao.class);
        bind(IFermentDao.class).to(JpaFermentDao.class);
        bind(IFermentLogDao.class).to(JpaFermentLogDao.class);
        bind(IFermentLogEntryDao.class).to(JpaFermentLogEntryDao.class);
        bind(IFinishDao.class).to(JpaFinishDao.class);
        bind(IFinishLogDao.class).to(JpaFinishLogDao.class);
        bind(IFinishLogEntryDao.class).to(JpaFinishLogEntryDao.class);
        bind(IIngredientDao.class).to(JpaIngredientDao.class);
        bind(IProcessDao.class).to(JpaProcessDao.class);
        bind(IProcessStepDao.class).to(JpaProcessStepDao.class);
        bind(IRecipeDao.class).to(JpaRecipeDao.class);
        bind(IRecipeComponentDao.class).to(JpaRecipeComponentDao.class);
        bind(IVesselDao.class).to(JpaVesselDao.class);
    }

    @Override
    protected void bindApplicationExceptionMappers() {
        bind(EntityNotFoundExceptionMapper.class);
        bind(DaoExceptionMapper.class);
        bind(UnauthenticatedExceptionMapper.class);
    }

    @Override
    protected Map<String, String> configureApplicationParameters(Map<String, String> parameters) {
        parameters.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        parameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, "boozelogger.api");

        // wire up the @RolesAllowed resource filter
        parameters.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory");

        // wire up our custom @RolesBanned resource filter annotation.
        parameters.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "unification.security.RolesBannedResourceFilterFactory");

        // Install logging filters to log all incoming and outgoing traffic.
        // DEVELOPMENT ONLY -- NOT FOR PRODUCTION USE
        parameters.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS,
                "com.sun.jersey.api.container.filter.LoggingFilter");
        parameters.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS,
                "com.sun.jersey.api.container.filter.LoggingFilter");

        return parameters;
    }

    protected Properties bindApplicationNamedProperties(Properties properties) {
        // API Version
        String apiVersion = System.getProperty(GrandUnificationModule.API_VERSION);

        // LDAP properties
        String ldapUrl = System.getProperty(GrandUnificationModule.LDAP_URL);
        String ldapAdminDN = System.getProperty(GrandUnificationModule.LDAP_ADMIN_DN);
        String ldapAdminPw  = System.getProperty(GrandUnificationModule.LDAP_ADMIN_PW);


        log.debug("Using API_VERSION: " + apiVersion);
        log.debug("Using LDAP_URL: " + ldapUrl);
        log.debug("Using LDAP_ADMIN_DN: " + ldapAdminDN);
        log.debug("Using LDAP_ADMIN_PW: " + ldapAdminPw);

        properties.put(GrandUnificationModule.API_VERSION, apiVersion);
        properties.put(GrandUnificationModule.LDAP_URL, ldapUrl);
        properties.put(GrandUnificationModule.LDAP_ADMIN_DN, ldapAdminDN);
        properties.put(GrandUnificationModule.LDAP_ADMIN_PW, ldapAdminPw);

        return properties;
    }
}
