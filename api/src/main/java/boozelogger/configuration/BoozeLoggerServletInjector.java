package boozelogger.configuration;

import unification.configuration.GrandUnificationModule;
import unification.configuration.GuiceServletInjector;

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
}
