package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;

public class MailCtrl implements IApplication {
	
	/* Attributs */
	private String name;
	
	private MailView view;

	/* Constructeur(s) */
	public MailCtrl() {
		view = new MailView();
	}

	/* Méthodes */
	public void start(PdaCtrl pda) {
		System.out.println("Début de l'application Mail Client");
	}
	public String getAppliName() {
		return name;
	}
	public JPanel getAppliPanel() {
		return view.getMainPanel();
	}
	public boolean close() {
		return true;
	}
	public void setAppliName(String theName) {
		this.name = theName;
	}
}
