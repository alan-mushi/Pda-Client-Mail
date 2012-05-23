package pdaNetwork.misc;

import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Manage the content of a mail : recipient, object, date and text.
 * <p>
 * <pre>
 * Usage Mailcontent in a MailClient instance :
 *   MailContent mailContent = new MailContent("target user","Object of the message","the message","expeditor user"); 
 *   mailClient.send(mailContent);
 * </pre>
 * @author PDA Server development team.
 */
public class MailContent {

    /**
     * Recipient of the mail
     */
    private String recipient;
	
    /**
     * Mail object.
     */
    private String object;
	
    /**
     * Text of the mail.
     */
    private String text;
	
    /**
     * Mail author.
     */
    private String expeditor;
	
    private String date;

    /**
     * Initialize the mail content. The date must be initilialize with setDate () method.
     * 
     * @param recipient The personn who will receive the mail.
     * @param object	The object of the mail.
     * @param text		The text of the mail.
     */    
    public MailContent (String recipient, String object, String text) {
    	this (recipient, object, text, null, null);
    }

    /**
     * Initialize the mail content. The date must be initilialize with setDate () method.
     * 
     * @param recipient The personn who will receive the mail.
     * @param object	The object of the mail.
     * @param text		The text of the mail.
     * @param expeditor The expeditor of the mail.
     */
    public MailContent (String recipient, String object, String text, String expeditor) {
	this (recipient, object, text, expeditor, null);
    }

    /**
     * Initialize the mail content. The date must be initilialize with setDate () method.
     * 
     * @param recipient The personn who will receive the mail.
     * @param object	The object of the mail.
     * @param text		The text of the mail.
     * @param expeditor The expeditor of the mail. Set to 'hiden' if null.
     * @param date      The creation date of the mail. Set automatically if null.
     */
    public MailContent (String recipient, String object, String text, String expeditor, String date) {
	this.recipient = recipient;
	this.object = object;
	this.text = text;
	this.expeditor = expeditor;
	if (expeditor == null || "".equals (expeditor))
	    this.expeditor = "hiden";
	this.date = date;
	if (date == null || "".equals (date))
	    setDate ();
    }

    /**
     * This method will return the recipient of the mail.
     * 
     * @return The recipient of the mail.
     */
    public String getRecipient () {
	return recipient;
    }
	
    /**
     * 
     * @return The expeditor of the mail.
     */
    public String getExpeditor (){
	return expeditor;
    }

    /**
     * This method will return the object of the mail.
     * 
     * @return The object of the mail.
     */
    public String getObject () {
	return object;
    }

    /**
     * This method will return the text of the mail.
     * 
     * @return The text of the mail.
     */
    public String getText () {
	return text;
    }
	
    /**
     * 
     * @param expeditor Define the expeditor of the mail.
     */
    public void setExpeditor (String expeditor) {
	this.expeditor = expeditor;
    }
	
    /**
     * Create and formate the current date to DD-MM-YYYY
     * 
     * @return the date.
     */
    public String getDate () {
	return date;
    }
	
    private static SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm");

    /**
     * 
     * Initialize the date of sent of the mail.
     */
    public void setDate () {
	date = dateFormat.format (new Date ());
    }
	
    /**
     * 
     * Initialize the date of send the mail for a String object.
     * 
     * @param date The date string.
     */
    public void setDate (String date) {
	this.date = date;
		
    }
	
    /**
     * This method convert a message the mail to an XML formatted message containing the mail.
     * 
     * @return A formatted XML Message.
     */
    public String toXML () {
	return
	    "<recipient>"+new String (Base64.encodeBase64 (recipient.getBytes ()))+"</recipient>"+
	    "<expeditor>"+new String (Base64.encodeBase64 (expeditor.getBytes ()))+"</expeditor>"+
	    "<object>"+new String (Base64.encodeBase64 (object.getBytes ()))+"</object>"+
	    "<date>"+new String (Base64.encodeBase64 (date.getBytes ()))+"</date>"+
	    "<text>"+new String (Base64.encodeBase64 (text.getBytes ()))+"</text>";
    }
}
