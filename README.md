# orientdb-javaee

This project is an utilities set to use OrientDB in a JavaEE environment. Basically it solve 3 problems:

* Injecting the database instance.
* Handle the database connection for request.
* Yo avoid serialization issues it automatically detach the objects when return from a resource method (annotated wiht @GET, @POST, etc...) 

For example, you can inject the database instance in your classes:

```java
@Inject
OObjectDatabaseTx db;
```
And when you do a query, you don't need to detach the object:

```java
@GET
public List<Chat> getAll()
{
  return db.command(new OSQLSynchQuery<Chat>("select * from Example")).execute();
}
```

This project was originally built to work with Jackson and RestEasy, but you can adapt to your needing.

## Configuration

Configure a provider that supports the conversion of a OrientDB proxed object to a stream.

```java
ResteasyDeployment deployment = new ResteasyDeployment();
deployment.setProviderClasses(Arrays.asList(OrientDBJacksonProvider.class.getName()));
```

Add the servlet Filter in order to handle the database connection. Furthermore the database connection is also stored in a thread local, so you can inject or retrieve it using OrientDBUtil.getDatabase() static method.

```java
.addFilter(new FilterInfo("/api", OrientDBFilter.class))
.addFilterUrlMapping("/api", "/*", DispatcherType.REQUEST);
```

Create a properties file named orientdb.properties and configure the properties below.
```
orientdb.entitymanager.packagename=com.example.domain
orientdb.database.password=root
orientdb.database.username=root
orientdb.database.url=remote:localhost/yourdatabase
```
