package pdaNetwork.misc;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;

/**
 * This class parse the XML configuration file.<br />
 * Read a XML file which contain configs values.<br />
 * <p>
 * Exemple of XML file :<br />
 * &lt;blockquote&gt;<br />
 * &lt;config&gt;<br />
 *
 *   &lt;debug&gt;0&lt;/debug&gt;<br />
 *
 *   &lt;socketType&gt;WebSocket&lt;/socketType&gt;<br />
 *
 *   &lt;remoteHost&gt;projets.iut-info-vannes.net&lt;/remoteHost&gt;<br />
 *   &lt;remotePort&gt;80&lt;/remotePort&gt;<br />
 *   &lt;uri&gt;/pda-server/pdaNetwork.php&lt;/uri&gt;<br />
 *
 *   &lt;proxyHost&gt;squidva.univ-ubs.fr&lt;/proxyHost&gt;<br />
 *   &lt;proxyPort&gt;3128&lt;/proxyPort&gt;<br />
 *
 *   &lt;startOfReq&gt;paquet&lt;/startOfReq&gt;<br />
 *   &lt;endOfReq&gt;paquet&lt;/endOfReq&gt;<br />
 * &lt;/config&gt;<br />
 * </blockquote>
 * </p>
 * 
 * @author PDA Server Developement Team 
 *
 */
public class ConfigConst {

    private static final List<String> communTokens =
	Arrays.asList ("debug", "socketType", "startOfReq", "endOfReq");
    private static final List<String> serverTokens =
	Arrays.asList ("listenPort", "logPath", "userPath", "mailPath");
    private static final List<String> clientTokens =
	Arrays.asList ("remoteHost", "remotePort", "proxyHost", "proxyPort", "uri");
    private static Hashtable<String,String> values = new Hashtable<String,String> ();

    public static final List<String> getTokens () {
	ArrayList<String> tokens = new ArrayList<String> (communTokens);
	tokens.addAll (serverTokens);
	tokens.addAll (clientTokens);
	return tokens;
    }

    public static final String get (String token) {
	return values.get (token);
    }

    public static void readConfigFile (String configFileName, boolean serverFlag) {
	try {
	    Hashtable<String,String> newValues = new Hashtable<String,String> ();
	    //Creating an Object File from the file designed by the path
	    File sourceFile = new File (configFileName);

	    //Defines a factory API that enables applications to obtain a parser that produces DOM object trees from XML documents.
	    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance ();
	    //This object will be use to get a Document from the XML file
	    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder ();
	    //Initialiazing the Document
	    Document doc = docBuilder.parse (sourceFile);
	    //Normalize the Document
	    doc.getDocumentElement ().normalize ();
	    //We create a NodeList, with all elements with the name: config
	    NodeList nodeLst = doc.getElementsByTagName ("config");
	    //Select the current Node
	    Node fstNode = nodeLst.item (0);
	    //We check the type
	    if (fstNode.getNodeType () == Node.ELEMENT_NODE) {
		for (String token : communTokens)
		    newValues.put (token, getValue (fstNode, token));
		for (String token : (serverFlag ? serverTokens : clientTokens))
		    newValues.put (token, getValue (fstNode, token));
	    }
	    // XXX Attention à startOfReq et le endOfReq (pour le <>)
	    values = newValues;
	} catch (ParserConfigurationException e) {
	    System.err.println ("Error in config file "+configFileName);
	    System.exit (-1);
	} catch (IOException e) {
	    System.err.println ("IOException in config file "+configFileName);
	    System.exit (-1);
	} catch (SAXException e) {
	    System.err.println ("Syntaxe error in config file "+configFileName);
	    System.exit (-1);
	}
    }

    /**
     * 
     * @param fstNode
     * @param valueName
     * @return the node value
     */
    private static String getValue (Node fstNode, String valueName) {
	//The Node become an Element
	Element fstElmnt = (Element) fstNode;
	//We create another NodeList from the Element, we select the socketType
	NodeList fstNmElmntLst = fstElmnt.getElementsByTagName (valueName);
	//We select the item (0) of this list
	Element fstNmElmnt = (Element) fstNmElmntLst.item (0);
	try {
	    //We select the Child of this NodeList	
	    NodeList fstNm = fstNmElmnt.getChildNodes ();
	    return fstNm.item (0).getNodeValue ();
	} catch (NullPointerException e) {
	    System.err.println ("Invalid config file ("+valueName+" missing).");
	    System.exit (-1);
	}
	return "";
    }

    private static int getPort (String portName) {
	try {
	    int port = Integer.parseInt (values.get (portName));
	    if (port > 65535 || port < 1)
		throw new NullPointerException ();
	    return port;
	} catch (NumberFormatException e) {                    
	    System.err.println ("Port  ("+portName+") must be an integer in config file");
	    System.exit (-1);                                                                    
	}                                                                                           
	return 0;
    }
	
    public static boolean getDebug () {
	try {
	    return Integer.parseInt (values.get ("debug")) != 0;
	} catch (NumberFormatException e) {
	}
	return false;
    }

    public static String getSocketType () {
	return values.get ("socketType");
    }
	
    public static String getStartOfReq () {
	// XXX a revoir
	return "<"+values.get ("startOfReq")+">";
    }
	
    public static String getEndOfReq () {
	// XXX a revoir
	return "</"+values.get ("endOfReq")+">";
    }
	
    public static int getListenPort () {
	return getPort ("listenPort");
    }

    public static String getLogPath () {
	return values.get ("logPath");
    }
	
    public static String getUserPath () {
	return values.get ("userPath");
    }

    public static String getMailPath () {
	return values.get ("mailPath");
    }

    public static String getRemoteHost () {
	return values.get ("remoteHost");
    }
    public static int getRemotePort () {
	return getPort ("remotePort");
    }

    public static String getProxyHost () {
	return values.get ("proxyHost");
    }

    public static int getProxyPort () {
	return getPort ("proxyPort");
    }

    public static String getUri () {
	return values.get ("uri");
    }
}
