package pdaNetwork.misc;

/**
 * Generals class of Connection exception for PdaNetwork
 * 
 * @author @author PDA Server development team.
 *
 */
public class ProtocolException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * Create a protocol exception with an error message.
     * 
     * @param message The error message.
     */
    public ProtocolException (String message) {
	super (message);
    }

    /**
     * 
     * Create a protocol exception with an error and a cause.
     * 
     * @param message The error message.
     * @param cause The exception which is the cause of this exception.
     */
    public ProtocolException (String message, Exception cause) {
	super (message, cause);
    }

    /**
     * Write the error in the logs files.
     */
    public void manageLog () {
	Log.writeLog ("error", getMessage ()+" <= "+getCause ().getMessage ());
	if (ConfigConst.getDebug ())
	    System.err.println ("error: "+getMessage ()+" <= "+getCause ().getMessage ());
    }
}
