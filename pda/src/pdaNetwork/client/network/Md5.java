package pdaNetwork.client.network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class manage the string conversion in md5.
 * 
 * @author PDA Server development team.
 */
public class Md5 {

    /**
     * @param password String to encode in md5.
     * 
     * @return An hashed string from the md5 string.
     */
    public static String encode (String password) {

	try {
	    byte[] hash = MessageDigest.getInstance ("MD5").digest (password.getBytes ());

	    StringBuffer hashString = new StringBuffer ();
	    for (int i = 0; i < hash.length; ++i) {
		String hex = Integer.toHexString (hash[i]);
		if (hex.length () == 1) {
		    hashString.append ('0');
		    hashString.append (hex.charAt (hex.length () - 1));
		} else
		    hashString.append (hex.substring (hex.length () - 2));
	    }
	    return hashString.toString ();
	} catch (NoSuchAlgorithmException e) {
	    throw new Error ("no MD5 support in this VM");
	}
    }
}
