package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import LogicComponents.Contact;
import LogicComponents.ContactManager;

public class SortingButton extends JRadioButton{
	private ContactManager contactManager;
	private ContactJList contactJList;
	
	ActionListener al = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			contactManager.sorting();
			contactJList.setData();
		}
		
	};
	
	public SortingButton(String name) {
		super(name);
		addActionListener(al);
	}

	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}

	public void setContactJList(ContactJList contactJList) {
		this.contactJList = contactJList;
	}
	
	
}
