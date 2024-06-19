package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import LogicComponents.Contact;
import LogicComponents.ContactManager;

public class DeleteButton extends JButton {
	private DetailedPanel detailedPanel;
	private ContactJList contactJList;
	private NewButton newButton;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int index = contactJList.getSelectedIndex();
			detailedPanel.clearTextFields();
			contactJList.removeContact(index);
			setEnabled(false);
			newButton.setEnabled(false);
			contactJList.saveFile();
		}
		
	};
	
	public DeleteButton(DetailedPanel dp) {
		super("Törlés");
		addActionListener(al);
		detailedPanel = dp;
		
	}
	
	public void setContactJList(ContactJList cJList) {
		contactJList = cJList;
	}
	
	public void setNewButton(NewButton newButton) {
		this.newButton = newButton;
	}
}
