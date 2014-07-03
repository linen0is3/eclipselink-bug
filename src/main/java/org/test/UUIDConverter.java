
package org.test;

import java.util.UUID;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

/**
 * eclipselink type converter for {@link UUID}
 *
 */
public class UUIDConverter implements Converter {

	private static final long serialVersionUID=1L;

	@Override
	public byte[] convertObjectValueToDataValue(Object o, Session sn) {
		if (null==o) {
			return null;
		}
		if (!(o instanceof UUID)) {
			throw new IllegalArgumentException("need UUID, got: "+o.getClass().getCanonicalName());
		}
		return UUIDUtil.toBytes((UUID) o);
	}

	@Override
	public UUID convertDataValueToObjectValue(Object o, Session sn) {
		if (null==o) {
			return null;
		}
		if (!(o instanceof byte[])) {
			throw new IllegalArgumentException("need byte[], got: "+o.getClass().getCanonicalName());
		}
		return UUIDUtil.fromBytes((byte[]) o);
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public void initialize(DatabaseMapping dm, Session sn) {
		dm.getField().setColumnDefinition("BINARY(16)");
		dm.getField().setSqlType(java.sql.Types.BINARY);
		dm.getField().setType(byte[].class);
		dm.getField().setLength(16);
	}
}
