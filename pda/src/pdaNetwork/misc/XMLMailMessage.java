package pdaNetwork.misc;

import java.util.Arrays;
import java.util.List;

/**
 * This class parse the XML request he received.
 * 
 * @author PDA Server development team.
 */
public class XMLMailMessage extends XMLMessage {

    private static List<String> tokens =
	Arrays.asList ("mail");

    /**
     * The constructor of XMLServerMessage.
     * 
     * @param xmlMessage The XML Message you want to analyze.
     * 
     * @throws ProtocolException If an error occured durant the parsing of a message, it's sent a ProtocolException. 
     */
    public XMLMailMessage (String xmlMessage) throws ProtocolException {
	super (xmlMessage, tokens);
    }
}
