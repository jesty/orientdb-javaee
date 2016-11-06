package com.nutcore.orientdb.javaee.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import javax.enterprise.inject.Produces;


/**
 * Created by davidecerbo on 05/11/2016.
 */
public class OrientDBProducer
{
    @Produces
    public OObjectDatabaseTx objectMapper() {
        return OrientDBUtil.getDatabase();
    }

}
