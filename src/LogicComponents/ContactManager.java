package LogicComponents;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JRadioButton;


public class ContactManager {
	private ArrayList<Contact> contacts = new ArrayList<Contact>();
	private JRadioButton nameSorting;
	
	
	public ContactManager() {
		loadContacts();
	}
	
	public void sorting() {
		for (Contact contact: contacts) {
			contact.setCompareByName(nameSorting.isSelected());
		}
		Collections.sort(contacts);
	}
	
	public void saveContacts() {
		try {
			FileOutputStream fos = new FileOutputStream("Contacts.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(contacts);
			oos.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadContacts() {
		try {
			FileInputStream fis = new FileInputStream("Contacts.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			contacts = (ArrayList<Contact>) ois.readObject();
			ois.close();
		}
		catch (FileNotFoundException e) {
			saveContacts();
			System.out.print("Data file not found! New data file was created!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void saveModifiedContact(int i, Contact mc) {
		contacts.set(i, mc);
	}
	
	public void removeContact(int i) {
		contacts.remove(i);
	}
	
	public void addContact(Contact nc) {
		contacts.add(nc);
	}
	
	public void setRadioButton(JRadioButton radioButton) {
		nameSorting = radioButton;
	}
	
}
