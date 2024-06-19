package GUIComponents;

import java.awt.event.*;

import javax.swing.*;

public class RemovePhoneNumberButton extends JButton {
	DetailedPanel detailedPanel;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			detailedPanel.removePhoneField();
		}
		
	};
	
	public RemovePhoneNumberButton(DetailedPanel dp) {
		super("Szám törlése");
		this.addActionListener(al);
		this.detailedPanel = dp;
	}

}
