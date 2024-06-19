package GUIComponents;

import java.awt.event.*;

import javax.swing.*;

public class AddPhoneNumberButton extends JButton {
	private DetailedPanel detailedPanel;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			detailedPanel.addPhoneField();
		}
		
	};
	
	public AddPhoneNumberButton(DetailedPanel dp) {
		super("Sz�m hozz�ad�sa");
		this.addActionListener(al);
		this.detailedPanel = dp;
	}

}
