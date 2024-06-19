package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import LogicComponents.ContactManager;

public class NewButton extends JButton{
	private DetailedPanel detailedPanel;
	private ContactJList contactJList;
	private DeleteButton deleteButton;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			contactJList.clearSelection();
			detailedPanel.clearTextFields();
			setEnabled(false);
			deleteButton.setEnabled(false);
		}
		
	};
	
	public NewButton(DetailedPanel dp) {
		super("Új");
		addActionListener(al);
		detailedPanel = dp;
	}
	
	public void setContactJList(ContactJList cJList) {
		contactJList = cJList;
	}
	
	public void setDeleteButton(DeleteButton db) {
		deleteButton = db;
	}

}
