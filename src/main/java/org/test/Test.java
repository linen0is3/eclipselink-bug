package org.test;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("clientTest");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		UUID clientId = UUID.fromString("95e88b06-596e-4f58-a0c8-3e53f74b4fe4");
		Client client = new Client(clientId, "1", "test 1");
		entityManager.persist(client);
		entityManager.flush();
		entityManager.getTransaction().commit();

		TypedQuery<Client> queryExternalOrId = entityManager.createQuery("SELECT client\n"
				+ " FROM Client client\n"
				+ " WHERE client.id = :id0 OR client.externalId = :externalId1", Client.class);
		queryExternalOrId.setParameter("id0", clientId);
		queryExternalOrId.setParameter("externalId1", "270");
		// success
		System.err.println(queryExternalOrId.getResultList());

		TypedQuery<Client> queryNamedId = entityManager.createNamedQuery("Client.findById", Client.class);
		queryNamedId.setParameter("id", clientId);
		// success
		System.err.println(queryExternalOrId.getResultList());

		TypedQuery<Client> queryId = entityManager.createQuery("SELECT client\n"
				+ " FROM Client client\n"
				+ " WHERE client.id = :id0", Client.class);
		queryId.setParameter("id0", clientId);
//		[EL Warning]: 2014-07-03 12:32:53.552--UnitOfWork(190548289)--Exception [EclipseLink-3001] (Eclipse Persistence Services - 2.4.2.v20130514-5956486): org.eclipse.persistence.exceptions.ConversionException
//Exception Description: The object [95e88b06-596e-4f58-a0c8-3e53f74b4fe4], of class [class java.util.UUID], could not be converted to [class [B].
//Exception in thread "main" Local Exception Stack: 
//Exception [EclipseLink-3001] (Eclipse Persistence Services - 2.4.2.v20130514-5956486): org.eclipse.persistence.exceptions.ConversionException
//Exception Description: The object [95e88b06-596e-4f58-a0c8-3e53f74b4fe4], of class [class java.util.UUID], could not be converted to [class [B].
//	at org.eclipse.persistence.exceptions.ConversionException.couldNotBeConverted(ConversionException.java:71)
//	at org.eclipse.persistence.internal.helper.ConversionManager.convertObjectToByteArray(ConversionManager.java:344)
//	at org.eclipse.persistence.internal.helper.ConversionManager.convertObject(ConversionManager.java:134)
//	at org.eclipse.persistence.internal.databaseaccess.DatasourcePlatform.convertObject(DatasourcePlatform.java:162)
//	at org.eclipse.persistence.internal.descriptors.ObjectBuilder.extractPrimaryKeyFromRow(ObjectBuilder.java:2573)
//	at org.eclipse.persistence.internal.descriptors.ObjectBuilder.extractPrimaryKeyFromExpression(ObjectBuilder.java:2420)
//	at org.eclipse.persistence.internal.queries.ExpressionQueryMechanism.checkCacheForObject(ExpressionQueryMechanism.java:866)
//	at org.eclipse.persistence.queries.ReadObjectQuery.checkEarlyReturnLocal(ReadObjectQuery.java:243)
//	at org.eclipse.persistence.queries.ObjectLevelReadQuery.checkEarlyReturn(ObjectLevelReadQuery.java:838)
//	at org.eclipse.persistence.queries.DatabaseQuery.execute(DatabaseQuery.java:789)
//	at org.eclipse.persistence.queries.ObjectLevelReadQuery.execute(ObjectLevelReadQuery.java:1109)
//	at org.eclipse.persistence.queries.ReadObjectQuery.execute(ReadObjectQuery.java:421)
//	at org.eclipse.persistence.queries.ObjectLevelReadQuery.executeInUnitOfWork(ObjectLevelReadQuery.java:1197)
//	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.internalExecuteQuery(UnitOfWorkImpl.java:2879)
//	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1607)
//	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1589)
//	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1554)
//	at org.eclipse.persistence.internal.jpa.QueryImpl.executeReadQuery(QueryImpl.java:231)
//	at org.eclipse.persistence.internal.jpa.QueryImpl.getResultList(QueryImpl.java:403)
//	at org.test.Test.main(Test.java:40)
		System.err.println(queryId.getResultList());

		entityManager.close();
		entityManagerFactory.close();
	}

}
