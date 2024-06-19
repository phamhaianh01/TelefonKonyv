package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import LogicComponents.Contact;
import LogicComponents.ContactManager;

public class SaveButton extends JButton {
	private DetailedPanel detailedPanel;
	private ContactJList contactJList;
	private NewButton newButton;
	private DeleteButton deleteButton;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = contactJList.getSelectedIndex();
			Contact modifiedContact = detailedPanel.getModifiedContact();
			if (modifiedContact.getFullName().equals("")) {
				return;
			}
			contactJList.saveModifiedContact(index, modifiedContact);
			
			detailedPanel.clearTextFields();
			if (index != -1) {
				deleteButton.setEnabled(true);
				newButton.setEnabled(true);
			}
			contactJList.saveFile();
		}
		
	};
	
	public SaveButton(DetailedPanel dp) {
		super("Mentés");
		addActionListener(al);
		detailedPanel = dp;
	}
	public void setContactJList(ContactJList cJList) {
		contactJList = cJList;
	}

	public void setNewButton(NewButton newButton) {
		this.newButton = newButton;
	}

	public void setDeleteButton(DeleteButton deleteButton) {
		this.deleteButton = deleteButton;
	}
	
}
