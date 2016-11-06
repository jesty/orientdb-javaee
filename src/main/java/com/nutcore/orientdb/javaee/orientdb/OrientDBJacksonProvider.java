package com.nutcore.orientdb.javaee.orientdb;

import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import javassist.util.proxy.Proxy;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by davidecerbo on 05/11/2016.
 */
@Provider
@Consumes({"application/json", "text/json"})
@Produces({"application/json", "text/json"})
public class OrientDBJacksonProvider extends JacksonJsonProvider
{

    @Inject
    private OObjectDatabaseTx db;

    @Override
    public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException
    {
        Object detached;
        if (value instanceof Collection)
        {
            detached = ((Collection) value)
                    .stream()
                    .map(this::getObject)
                    .collect(Collectors.toList());
        } else
        {
            detached = getObject(value);
        }
        super.writeTo(detached, detached.getClass(), genericType, annotations, mediaType, httpHeaders, entityStream);
    }

    private Object getObject(Object value)
    {
        Object detached;
        if (value instanceof Proxy)
        {
            detached = db.detachAll(value, true);

        } else
        {
            detached = value;
        }
        return detached;
    }

}
