
package org.test;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Index;

@Entity
@Table(name = "client")
@NamedQueries({
	@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
	@NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
	@NamedQuery(name = "Client.findByExternalId", query = "SELECT c FROM Client c WHERE c.externalId = :externalId"),
	@NamedQuery(name = "Client.findByIds", query = "SELECT c FROM Client c WHERE c.id IN :ids"),
	@NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name")})
@Converter(name = "uuidConverter", converterClass = UUIDConverter.class)
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false, updatable = false, length = 16)
	@Lob
	@Convert("uuidConverter")
	private UUID id;
	@Index
	@Basic(optional = true)
	@Column(name = "externalId", length = 255, nullable = true, unique = false)
	private String externalId;
	@Column(name = "name", nullable = false, length = 255)
	private String name;

	public Client() {
	}

	public Client(UUID id) {
		this.id = id;
	}

	public Client(UUID id, String externalId, String name) {
		this.id = id;
		this.externalId = externalId;
		this.name = name;
	}

	public Client(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Client)) {
			return false;
		}
		Client other = (Client) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Client[ id=" + id + " ]";
	}
}
