package dk.four.group.playerosgi;

import dk.four.group.common.services.IEntityProcessingService;
import dk.four.group.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    
    private ServiceRegistration r;
    private ServiceRegistration s;

    @Override
    public void start(BundleContext context) throws Exception {
        r = context.registerService(IGamePluginService.class, new EntityPlugin(), null);
        s = context.registerService(IEntityProcessingService.class, new PlayerControlSystem(), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        r.unregister();
        s.unregister();
    }

}
