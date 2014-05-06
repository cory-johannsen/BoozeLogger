package boozelogger.configuration;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import unification.configuration.GrandUnificationModule;
import unification.configuration.GuiceServletInjector;
import unification.configuration.LDAPShiroConfigurationModule;

import javax.servlet.ServletContext;

/**
 * User: cjohannsen
 * Date: 5/5/14
 * Time: 12:30 PM
 */
public class BoozeLoggerServletInjector extends GuiceServletInjector {

    @Override
    protected GrandUnificationModule createApplicationModule() {
        return new BoozeLoggerModule();
    }

    protected LDAPShiroConfigurationModule createShiroConfigurationModule(ServletContext context) {
        return new BoozeLoggerShiroConfigurationModule(context);
    }
}
