package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailParamCtrl implements ActionListener {
	
	private MailParamView view;
	
	public MailParamCtrl(MailParamView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailMenuView(this.view.getMainPanel());
		}
	}
}
