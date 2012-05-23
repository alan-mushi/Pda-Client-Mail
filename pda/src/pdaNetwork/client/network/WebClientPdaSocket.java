package pdaNetwork.client.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.commons.codec.binary.Base64;

import pdaNetwork.misc.ConfigConst;
import pdaNetwork.misc.ProtocolException;

/**
 * This class extends PdaSocket, it will create socket through a proxy using an HTML Tag.
 * 
 * @author PDA Server development team.
 */
public class WebClientPdaSocket extends ClientPdaSocket {

    private String httpRequest;

    /**
     * Create a WebClientPdaSocket.
     * 
     * @param username Name of the user of the service.
     * @param password the password of the user.
     * @param service Service called.
     * 
     * @throws ProtocolException Throw an exception if username, password or service are null.
     */
    public WebClientPdaSocket (String username, String password, String service) throws ProtocolException {
	super (username, password, service);
	if (debug)
	    System.err.println ("WebClientPdaSocket: >>>");

	// Différence avec ClientPdaSocket
	String proxyHost = ConfigConst.getProxyHost ();
	if ("noProxy".equals (proxyHost))
	    httpRequest =
		"POST "+ConfigConst.getUri ()+" HTTP/1.0\n"+
		"Host: "+remoteHost+"\n";
	else {
	    httpRequest =
		"POST http://"+remoteHost+":"+remotePort+ConfigConst.getUri ()+" HTTP/1.0\n"+
		"Host: "+remoteHost+"\n";
	    remoteHost = proxyHost;
	    remotePort = ConfigConst.getProxyPort ();
	}
	if (debug)
	    System.err.println ("WebClientPdaSocket: <<<");
    }

    /**
     * This method add the HTML tag to the xmlMessage.
     * 
     * @param xmlMessage The XML message you want to send.
     * 
     * @return An xml message tagged with HTML.
     */
    protected String addHTML (String xmlMessage) {
	if (debug)
	    System.err.println ("WebClientPdaSocket:addHTML >>>");
	return
	    httpRequest+
	    "Content-type: application/xml\n"+
	    "Content-Length: "+xmlMessage.length ()+"\n"+
	    "\n"+
	    xmlMessage;
    }

    /**
     * This method remove the HTML tag.
     * 
     * @param htmlMessage The HTML message you want to untag.
     * 
     * @return The message untagged.
     */
    protected String removeHTML (String htmlMessage) {
	String ret[] = htmlMessage.split ("\n");
	return ret [ret.length-1];
    }
}
