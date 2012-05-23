package pdaNetwork.misc;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.List;

/**
 * This class parse the XML request he received.
 * 
 * @author PDA Server development team.
 */
public class XMLMessage {
	
    /**
     * This string contains the type of the request.
     */
    private String typeReq;
	
    /**
     * The node list of the XML.
     */
    private NodeList nodeLst;

    /**
     * The constructor of XMLServerMessage.
     * 
     * @param xmlMessage The XML Message you want to analyze.
     * 
     * @throws ProtocolException If an error occured durant the parsing of a message, it's sent a ProtocolException. 
     */
    public XMLMessage (String xmlMessage, List<String> tokens) throws ProtocolException {

	try {
	    if (xmlMessage == null || xmlMessage.equals (""))
		throw new ProtocolException ("xmlMessage ne doit pas etre vide");
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();
	    DocumentBuilder db = factory.newDocumentBuilder ();
	    InputSource inStream = new InputSource ();
	    inStream.setCharacterStream (new StringReader (xmlMessage));
	    Document doc;
	    doc = db.parse (inStream);
	    doc.getDocumentElement ().normalize ();
	    for (String token : tokens)
		if (doc.getElementsByTagName (token).getLength () != 0) {
		    typeReq = token;
		    nodeLst = doc.getElementsByTagName (token);
		    return;
		}
	    throw new ProtocolException ("type de message inconnu :"+xmlMessage);
 	} catch (ProtocolException e) {
	    throw e;
 	} catch (Exception e) {
	    throw new ProtocolException ("erreur de XML: " + e.getMessage (), e);
	}
    }

    /**
     * Accessor to the type of request.
     * 
     * @return It returns the type of request.
     */
    public String getTypeReq () {
	return typeReq;
    }

    /**
     * Accessor to the type of request.
     * 
     * @param name Name of the attribute you want to have.
     * 
     * @return	The attribute corresponding to the name you give.
     * 
     * @throws ProtocolException If an error occured when the method get an attribute, it's send a ProtocolException. 
     */
    public String getAttribute (String name) throws ProtocolException {
	Node fstNode = nodeLst.item (0);
	if (fstNode.getNodeType () == Node.ELEMENT_NODE) {
	    Element fstElmnt = (Element) fstNode;
	    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName (name);
	    Element fstNmElmnt = (Element) fstNmElmntLst.item (0);
	    if (fstNmElmnt != null){
		NodeList fstNm = fstNmElmnt.getChildNodes ();
		return fstNm.item (0).getNodeValue ();
	    }
	}
	throw new ProtocolException ("erreur protocol: attribut " + name + "inexistant");
    }
}
