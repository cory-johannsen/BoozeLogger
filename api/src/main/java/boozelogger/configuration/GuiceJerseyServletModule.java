package boozelogger.configuration;

import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.shiro.guice.web.GuiceShiroFilter;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import unification.configuration.SLF4JTypeListener;
import unification.service.ObjectMapperProvider;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * User: cjohannsen
 * Date: 5/2/14
 * Time: 10:14 AM
 */
public class GuiceJerseyServletModule extends JerseyServletModule {

    // JPA properties
    private static final String JPA_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String JPA_JDBC_DRIVER = "javax.persistence.jdbc.driver";
    private static final String JPA_JDBC_URL = "javax.persistence.jdbc.url";
    private static final String JPA_JDBC_USER = "javax.persistence.jdbc.user";
    private static final String JPA_JDBC_PASSWORD = "javax.persistence.jdbc.password";
    private static final String JPA_PERSISTENCE_UNIT = "jpa.persistence.unit";

    @Override
    protected void configureServlets() {
        super.configureServlets();
        filter("/*").through(GuiceShiroFilter.class);

        // Load the persistence module properties from the environment
        Properties jpaProperties = new Properties();
        String jpaDialect = System.getProperty(JPA_HIBERNATE_DIALECT);
        String jpaDriver = System.getProperty(JPA_JDBC_DRIVER);
        String jpaUrl = System.getProperty(JPA_JDBC_URL);
        String jpaUser = System.getProperty(JPA_JDBC_USER);
        String jpaPassword = System.getProperty(JPA_JDBC_PASSWORD);
        String jpaPersistenceUnit = System.getProperty(JPA_PERSISTENCE_UNIT);

        System.out.println("Using hibernate dialect " + jpaDialect);
        System.out.println("Using JDBC driver " + jpaDriver);
        System.out.println("Using JDBC URL " + jpaUrl);
        System.out.println("Using JDBC user " + jpaUser);
        System.out.println("Using JDBC password " + jpaPassword);
        System.out.println("Using JPA persistence unit " + jpaPersistenceUnit);

        install(new JpaPersistModule("jpaPersistenceUnit").properties(jpaProperties));
        filter("/*").through(PersistFilter.class);

        // Bind the InitialContext implementation class to the Context interface
        // using singleton semantics
        bind(Context.class).to(InitialContext.class).in(Singleton.class);

        //Bind SLF4J Logging listener
        bindListener(Matchers.any(), new SLF4JTypeListener());

        // Bind an ObjectMapper provider to support seamless Jasper JSON
        // serialization/deserialization
        bind(ObjectMapperProvider.class).asEagerSingleton();

        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // hook Jackson into Jersey as the POJO <-> JSON exceptionMapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        parameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, "boozelogger.api");

        // wire up the @RolesAllowed resource filter
        parameters.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory");

        // wire up our custom @RolesBanned resource filter annotation.
        parameters.put(PackagesResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
                "com.vendscreen.security.RolesBannedResourceFilterFactory");

        // Install logging filters to log all incoming and outgoing traffic.
        // DEVELOPMENT ONLY -- NOT FOR PRODUCTION USE
        parameters.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS,
                "com.sun.jersey.api.container.filter.LoggingFilter");
        parameters.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS,
                "com.sun.jersey.api.container.filter.LoggingFilter");



        // Bind Guice Jersey Servlet container to the path "/*"
        serve("/*").with(GuiceContainer.class, parameters);
    }

    @Provides
    @Singleton
    Validator provideValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
