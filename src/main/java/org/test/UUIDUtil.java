package org.test;

import java.nio.ByteBuffer;
import java.util.UUID;

public final class UUIDUtil {

	private UUIDUtil() {
	}

	/**
	 * construct UUID from bytes
	 *
	 * @param buffer the bytes
	 * @return the UUID
	 */
	public static UUID fromBytes(byte[] buffer) {
		if (16 != buffer.length) {
			throw new IllegalArgumentException("need 16 bytes");
		}
		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
		return new UUID(byteBuffer.getLong(0), byteBuffer.getLong(8));
	}

	/**
	 * get binary representation of UUID
	 *
	 * @param uuid the UUID
	 * @return the bytes representing the UUID wrapped in a {@link ByteBuffer}
	 */
	private static ByteBuffer toByteBuffer(UUID uuid) {
		return ByteBuffer.allocate(16).putLong(uuid.getMostSignificantBits()).putLong(uuid.getLeastSignificantBits());
	}

	/**
	 * get binary representation of UUID
	 *
	 * @param uuid the UUID
	 * @return the bytes representing the UUID
	 */
	public static byte[] toBytes(UUID uuid) {
		return toByteBuffer(uuid).array();
	}
}
