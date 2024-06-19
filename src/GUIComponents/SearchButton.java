package GUIComponents;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import LogicComponents.Contact;
import LogicComponents.ContactManager;


public class SearchButton extends JButton {
	private ContactManager contactManager;
	private ContactJList contactJList;
	private JTextField textField;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (textField.getText().equals("")) {
				return;
			}
			
			int firstIndex = -1;
			Boolean found = false;
			
			for (int i = 0; i < contactManager.getContacts().size(); i++) {
				
				if (contactManager.getContacts().get(i).getFullName().contains(textField.getText())) {
					if (firstIndex == -1) {
						firstIndex = i;
					}
					if (i <= contactJList.getSelectedIndex()) {
						continue;
					}
					contactJList.setSelectedIndex(i);
					found = true;
					break;
				}
			}
			if (!found && firstIndex != -1) {
				contactJList.setSelectedIndex(firstIndex);
			}
			
			
		}
	};
	
	
	public SearchButton() {
		super("Keres");
		this.addActionListener(al);
	}


	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}


	public void setContactJList(ContactJList contactJList) {
		this.contactJList = contactJList;
	}
	
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
	
}
