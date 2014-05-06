package boozelogger.configuration;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.name.Named;
import unification.configuration.GrandUnificationModule;
import unification.configuration.LDAPShiroConfigurationModule;
import unification.security.SharedSecretAuthenticatingFilter;

import javax.servlet.ServletContext;

/**
 * User: cjohannsen
 * Date: 5/6/14
 * Time: 11:42 AM
 */
public class BoozeLoggerShiroConfigurationModule extends LDAPShiroConfigurationModule {

    public BoozeLoggerShiroConfigurationModule(ServletContext servletContext) {
        super(servletContext);
    }

    /* (non-Javadoc)
     * @see unification.configuration.GuiceShiroConfigurationModule#configureFilterChains()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void configureFilterChains() {
        Key<SharedSecretAuthenticatingFilter> SHARED_SECRET = Key.get(SharedSecretAuthenticatingFilter.class);

        // API Version
        String apiVersion = System.getProperty(GrandUnificationModule.API_VERSION);
        System.out.println("LDAPShiroConfigurationModule.configureFilterChains  - using API version " + apiVersion);

        // Filters are evaluated in the order in which they are defined so the least specific should be added as the last filter
        // chain. E.g. /** will match any url so  it should be the last added to the the filters collection.

        addFilterChain("/" + apiVersion + "/*", NO_SESSION_CREATION, SHARED_SECRET);
        addFilterChain("/**", ANON);
    }
}
