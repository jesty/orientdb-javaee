package com.nutcore.orientdb.javaee.orientdb;

import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by davidecerbo on 06/11/2016.
 */
public class OrientDBServletContextListener implements ServletContextListener
{

    private OServer server;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        try
        {
            server = OServerMain.create();
            server = server.startup(OrientDBServletContextListener.class.getClassLoader().getResourceAsStream("db.config.xml"));
            server.activate();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        if (server != null)
        {
            server.shutdown();
        }
    }
}
