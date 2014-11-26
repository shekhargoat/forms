package com.forms.server.dto;
import java.io.Serializable;
import java.util.UUID;

import javax.naming.InitialContext;

import com.dto.persistence.IDtoPersistence;
import com.dto.utils.DtoMappingUtils;

// TODO: Auto-generated Javadoc

/**
 * The Class AbsUUID.
 */
public abstract class SuperCommonDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -640345134233408383L;

	/** The Constant hexArray. */
	final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	
	/** The id. */
	public Integer id;
	
	/** The sid. */

	public String sid;
	
	public SuperCommonDTO() {
		try {
			Object obj = new InitialContext().lookup("java:global/repositoryservice/repositoryservice-ejb/DtoPersistenceImpl!com.dto.persistence.IDtoPersistence");
			if(obj instanceof IDtoPersistence){
				dtoPersistence = (com.dto.persistence.IDtoPersistence) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		dtoPersistence = ProducerUtils.getDtoPersistenceRemote();
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the sid.
	 *
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	
	/**
	 * Sets the sid.
	 *
	 * @param sid the new sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	public byte[] hexStringToByteArray() throws Exception{
		return hexStringToBytes(generateSidIfNull());
	}

	public String generateSid() {
		return UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
	}
	
	public String generateSidIfNull() throws Exception {
		if (sid != null && !sid.isEmpty()) {
			if (sid.length() != 64) {
				throw new Exception("Sid should be 64 character length");
			}
			return sid;
		} else {
			sid = generateSid();
			return sid;
		}
	}
	/**
	 * Hex string to byte array.
	 *
	 * @param s the s
	 * @return the byte[]
	 */
	public byte[] hexStringToBytes(String sid) {
	    int len = sid.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(sid.charAt(i), 16) << 4)
	                             + Character.digit(sid.charAt(i+1), 16));
	    }
	    return data;
	}
	
	/**
	 * Bytes to hex.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	public  String bytesToHexString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
