package com.nutcore.orientdb.javaee.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by davidecerbo on 06/11/2016.
 */
public class OrientDBFactory
{

    private String databaseURL;
    private String userName;
    private String password;
    private String entityManagerPackageName;

    @PostConstruct
    public void init()
    {
        InputStream inputStream  = OrientDBFilter.class.getClassLoader().getResourceAsStream("orientdb.properties");
        Properties properties = new Properties();
        try
        {
            properties.load(inputStream);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        databaseURL = properties.getProperty("orientdb.database.url");
        userName = properties.getProperty("orientdb.database.username");
        password = properties.getProperty("orientdb.database.password");
        entityManagerPackageName = properties.getProperty("orientdb.entitymanager.packagename");
    }

    public OObjectDatabaseTx newInstance()
    {
        OObjectDatabaseTx db = new OObjectDatabaseTx(databaseURL).open(userName, password);
        db.getEntityManager().registerEntityClasses(entityManagerPackageName);
        return db;
    }
}
