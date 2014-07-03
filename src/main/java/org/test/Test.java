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
		// The object [95e88b06-596e-4f58-a0c8-3e53f74b4fe4], of class [class java.util.UUID], could not be converted to [class [B].
		System.err.println(queryId.getResultList());
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
