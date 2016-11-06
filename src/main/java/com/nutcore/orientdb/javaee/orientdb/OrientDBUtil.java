package com.nutcore.orientdb.javaee.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Created by davidecerbo on 04/11/2016.
 */
public class OrientDBUtil
{

    private static final ThreadLocal<OObjectDatabaseTx> db = new ThreadLocal<>();

    public static OObjectDatabaseTx getDatabase()
    {
        return db.get();
    }

    static void setDatabase(OObjectDatabaseTx oObjectDatabaseTx)
    {
        db.set(oObjectDatabaseTx);
    }

}
