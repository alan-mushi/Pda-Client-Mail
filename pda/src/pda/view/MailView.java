package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;

public class MailView {
	
	//Panel principal de l'application Mail Client
	private JPanel mainPanel;
	
	public MailView() {
		mainPanel = new JPanel();
		mainPanel.add(new JLabel("test"));
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
