package pdaNetwork.test.service;

import java.util.ArrayList;
import java.util.Iterator;

import pdaNetwork.misc.ConfigConst;
import pdaNetwork.misc.MailContent;
import pdaNetwork.misc.ProtocolException;

import pdaNetwork.client.service.MailClient;

public class TestMailClient {

    public static void main (String[] args) {
	try {
	    String fileName = "data/xml/pdaServer/configClient.xml";
	    if (args.length > 1) {
		System.err.println ("Usage : java "+TestMailClient.class.getName ()+" configFileName");
		System.exit (1);
	    }
	    if (args.length > 0)
		fileName = args [0];
	    System.err.println ("Use "+fileName);
	    ConfigConst.readConfigFile (fileName, false);

	    System.out.println ("Envoie de 2 messages de user1 à user2");
	    MailClient sender = new MailClient ("user1", "mdp");
	    sender.send (new MailContent ("user2", "Objet 1", "Le texte 1"));
	    sender.send (new MailContent ("user2", "Objet 2", "Le texte 2"));
	    sender.close ();

	    System.out.println ("recuperation de tous les messages de user2");
	    MailClient receiver = new MailClient ("user2", "mdp");

	    for (String id : receiver.getHeaders ()) {
		MailContent email = receiver.receive (id);
		System.out.println ("expediteur : " + email.getExpeditor ());
		System.out.println ("recepteur : " + email.getRecipient ());
		System.out.println ("date : " + email.getDate ());
		System.out.println ("objet : " + email.getObject ());
		System.out.println ("texte : " + email.getText ());
		System.out.println ("-------------------------------------");
	    }

	    // destruction éventuelle de tous les messages de user2 sur le serveur
	    System.out.println ("destruction de tous les messages de user2");
	    for (String id : receiver.getHeaders ()) {
		receiver.delete (id);
	    }
	    receiver.close ();

	} catch (ProtocolException e) {
	    e.printStackTrace ();
	}
    }
}
