package com.nutcore.orientdb.javaee.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import javax.inject.Inject;
import javax.servlet.*;
import java.io.IOException;

/**
 * Created by davidecerbo on 04/11/2016.
 */
public class OrientDBFilter implements Filter
{

    @Inject
    private OrientDBFactory orientDBFactory;

    public void init(FilterConfig filterConfig) throws ServletException
    {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {

        OObjectDatabaseTx db = orientDBFactory.newInstance();
        OrientDBUtil.setDatabase(db);
        try
        {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally
        {
            db.close();
        }
    }

    public void destroy()
    {

    }
}
