package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JRadioButton;
import javax.swing.ListModel;

import org.junit.jupiter.api.Test;

import GUIComponents.ContactJList;
import GUIComponents.DetailedPanel;
import LogicComponents.Contact;
import LogicComponents.ContactManager;

class LogicTest {


	@Test
	void testSingleContact() {
		
		String[] phoneNumbers = {"06302613888", "06301362749"};
		Contact contact = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers, "haianh@gmail.com");
		
		assertEquals(contact.getFullName(), "Hai Anh");
		assertEquals(contact.getNickname(), "Hai");
		assertEquals(contact.getAddress(), "Budapest");
		assertEquals(contact.getPhoneNumbers()[0], "06302613888");
		assertEquals(contact.getPhoneNumbers()[1], "06301362749");
		assertEquals(contact.getEmail(), "haianh@gmail.com");
	}
	
	@Test
	void testAddContact() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String[] phoneNumbers = {"06302613888", "06301362749"};
		Contact contact = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers, "haianh@gmail.com");
		ContactManager contactManager = new ContactManager();
		contactManager.setContacts(contacts);
		contactManager.addContact(contact);
		
		assertEquals(contacts.get(0).getFullName(), "Hai Anh");
	}
	
	@Test
	void testRemoveContact() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String[] phoneNumbers = {"06302613888", "06301362749"};
		Contact contact = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers, "haianh@gmail.com");
		String[] phoneNumbers2 = {"06308145864"};
		Contact soonToRemovedContact = new Contact("András", "Bandi", "Budapest", phoneNumbers2, "remove@gmail.com");
		ContactManager contactManager = new ContactManager();
		contactManager.setContacts(contacts);
		contactManager.addContact(soonToRemovedContact);
		contactManager.addContact(contact);
		contactManager.removeContact(0);
		
		assertEquals(contacts.get(0).getFullName(), "Hai Anh");
	}
	
	@Test
	void sortingByName() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String[] phoneNumbers1 = {"06302613888", "06301362749"};
		String[] phoneNumbers2 = {"06308145864"};
		String[] phoneNumbers3 = {"06308145864", "06303675975", "0630237343"};
		Contact contact1 = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers1, "haianh@gmail.com");
		Contact contact2 = new Contact("András", "Bandi", "Budapest", phoneNumbers2, "second@gmail.com");
		Contact contact3 = new Contact("Béla", "B", "Budapest", phoneNumbers3, "third@gmail.com");
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
		JRadioButton nameSorting = new JRadioButton();
		nameSorting.setSelected(true);
		ContactManager contactManager = new ContactManager();
		contactManager.setRadioButton(nameSorting);
		contactManager.setContacts(contacts);
		contactManager.sorting();
		
		assertEquals(contacts.get(0).getFullName(), "András");
		
	}
	
	@Test
	void sortingByEmail() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String[] phoneNumbers1 = {"06302613888", "06301362749"};
		String[] phoneNumbers2 = {"06308145864"};
		String[] phoneNumbers3 = {"06308145864", "06303675975", "0630237343"};
		Contact contact1 = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers1, "haianh@gmail.com");
		Contact contact2 = new Contact("András", "Bandi", "Budapest", phoneNumbers2, "second@gmail.com");
		Contact contact3 = new Contact("Béla", "B", "Budapest", phoneNumbers3, "third@gmail.com");
		contacts.add(contact1);
		contacts.add(contact2);
		contacts.add(contact3);
		JRadioButton nameSorting = new JRadioButton();
		nameSorting.setSelected(false);
		ContactManager contactManager = new ContactManager();
		contactManager.setRadioButton(nameSorting);
		contactManager.setContacts(contacts);
		contactManager.sorting();
		
		
		assertEquals(contacts.get(0).getFullName(), "Hai Anh");
		
	}
	
	@Test
	void modifyContact() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String[] phoneNumbers = {"06302613888", "06301362749"};
		Contact contact = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers, "haianh@gmail.com");
		Contact modifiedContact = new Contact("Hai Anh", "Anh", "Pécs", phoneNumbers, "haianh@gmail.com");
		ContactManager contactManager = new ContactManager();
		contactManager.setContacts(contacts);
		contactManager.addContact(contact);
		
		contactManager.saveModifiedContact(0, modifiedContact);
		
		assertEquals(contacts.get(0).getNickname(), "Anh");
		assertEquals(contacts.get(0).getAddress(), "Pécs");
	}
	
	@Test
	void ContactJListTest() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		String[] phoneNumbers1 = {"06302613888", "06301362749"};
		String[] phoneNumbers2 = {"06308145864"};
		String[] phoneNumbers3 = {"06308145864", "06303675975", "0630237343"};
		Contact contact1 = new Contact("Hai Anh", "Hai", "Budapest", phoneNumbers1, "haianh@gmail.com");
		Contact contact2 = new Contact("András", "Bandi", "Budapest", phoneNumbers2, "second@gmail.com");
		Contact contact3 = new Contact("Béla", "B", "Budapest", phoneNumbers3, "third@gmail.com");
		ContactManager contactManager = new ContactManager();
		contactManager.setContacts(contacts);
		contactManager.addContact(contact1);
		contactManager.addContact(contact2);
		contactManager.addContact(contact3);
		
		ContactJList contactJList = new ContactJList(contactManager, null);
		
		
		
		contactJList.setData();
		
		ListModel cJListData = contactJList.getModel();
		
		assertEquals(cJListData.getElementAt(0), "Hai Anh - haianh@gmail.com");
		assertEquals(cJListData.getElementAt(1), "András - second@gmail.com");
		assertEquals(cJListData.getElementAt(2), "Béla - third@gmail.com");
		
	}
	
	
}
