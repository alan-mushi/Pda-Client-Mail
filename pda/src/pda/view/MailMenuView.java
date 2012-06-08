package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailMenuView {
	/** Le JPanel principal récupéré de la classe parente*/
	private JPanel mainPanel;
	
	public MailMenuView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
	}
}
