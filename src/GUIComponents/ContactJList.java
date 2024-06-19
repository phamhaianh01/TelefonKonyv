package GUIComponents;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import LogicComponents.Contact;
import LogicComponents.ContactManager;

public class ContactJList extends JList{
	private ContactManager contactManager;
	private DetailedPanel detailedPanel;
	
	
	public ContactJList(ContactManager cm, DetailedPanel dp) {
		super();
		contactManager = cm;
		detailedPanel = dp; 
		
		addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = getSelectedIndex();
				if (index != -1) 
					detailedPanel.onContactSelected(contactManager.getContacts().get(index));
				else
					detailedPanel.disableDeleteAndNewButtons();
			}
		});
		
		setData();
	}
	
	
	public void setData() {
		ArrayList<String> contactList = new ArrayList<String>(); 
		for (Contact contact:contactManager.getContacts()) {
			contactList.add(contact.getFullName() + " - " + contact.getEmail());
		}
		setListData(contactList.toArray());
	}
	

	public void saveModifiedContact(int i, Contact mc) {
		if (i == -1) {
			contactManager.addContact(mc);
		}
		else {
			contactManager.saveModifiedContact(i, mc);
		}
	}
	
	
	public void removeContact(int i) {
		contactManager.removeContact(i);
		setData();
		
	}
	
	
	public void saveFile() {
		contactManager.saveContacts();
		contactManager.sorting();
		setData();
	}
	
	
}
