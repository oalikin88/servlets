package ru.ibs.aos.test_servlets;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Start main class");
        Server server = new Server(8099);
        String webappCat = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath(); //"/src/main/webapp/";
        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        int i = webappCat.lastIndexOf('/');
        context.setResourceBase(webappCat.substring(i));
       // context.setDescriptor("/WEB-INF/web.xml");
        context.setConfigurations(new Configuration[] {
                new AnnotationConfiguration(),
                new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new PlusConfiguration(),
                new MetaInfConfiguration(),
                new FragmentConfiguration(),
                new EnvConfiguration()
        });
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.dump(System.err);
        System.out.println("OK: server started");
        server.join();
    }
}
