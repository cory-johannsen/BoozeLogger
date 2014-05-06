package boozelogger.configuration;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import unification.configuration.GrandUnificationModule;
import unification.configuration.LDAPShiroConfigurationModule;

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
        // API Version
        String apiVersion = System.getProperty(GrandUnificationModule.API_VERSION);
        addFilterChain("/" + apiVersion + "/status", ANON);
        addFilterChain("/" + apiVersion + "*", AUTHC_BASIC);
    }
}
