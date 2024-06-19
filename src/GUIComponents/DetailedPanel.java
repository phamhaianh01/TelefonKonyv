package GUIComponents;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import LogicComponents.Contact;
import LogicComponents.ContactManager;


public class DetailedPanel extends JPanel {
	private ArrayList<JLabel> jlabels = new ArrayList<JLabel>();
	private ArrayList<JTextField> jTextFields = new ArrayList<JTextField>();
	private String[] labels = {"Név:", "Becenév:", "Lakcím:", "Email:", "Szám(ok):"};
	private ArrayList<JTextField> extraPhoneNumbers = new ArrayList<JTextField>();
	private SpringLayout layout = new SpringLayout();
	private JPanel content = new JPanel(layout);
	private AddPhoneNumberButton addingPhoneButton = new AddPhoneNumberButton(this);
	private RemovePhoneNumberButton removePhoneButton = new RemovePhoneNumberButton(this);
	private SaveButton saveButton;
	private NewButton newButton;
	private DeleteButton deleteButton;
	
	
	public void addPhoneField() {
		JTextField newField = new JTextField("", 20);
		
		content.add(newField);
		JTextField lastTF;
		if (extraPhoneNumbers.size() == 0) {
			lastTF = jTextFields.get(jTextFields.size() - 1 );
		}
		else {
			lastTF = extraPhoneNumbers.get(extraPhoneNumbers.size() - 1);
		}
		extraPhoneNumbers.add(newField);
		
		
		layout.putConstraint(SpringLayout.WEST, newField, 70, SpringLayout.WEST, content);
		layout.putConstraint(SpringLayout.NORTH, addingPhoneButton, 5, SpringLayout.SOUTH ,newField);
		layout.putConstraint(SpringLayout.NORTH, removePhoneButton, 5, SpringLayout.SOUTH ,newField);
		layout.putConstraint(SpringLayout.NORTH, newField, 5, SpringLayout.SOUTH, lastTF);
		layout.putConstraint(SpringLayout.SOUTH, content,5, SpringLayout.SOUTH, addingPhoneButton);
		revalidate();
		
	}
	
	
	
	public void removePhoneField() {
		int lastIndex = extraPhoneNumbers.size() - 1;
		
		if (lastIndex >= 0) {
			JTextField removingField = extraPhoneNumbers.get(lastIndex);
			content.remove(removingField);
			extraPhoneNumbers.remove(lastIndex);
			
			JTextField lastTF;
			if (extraPhoneNumbers.size() == 0) {
				lastTF = jTextFields.get(jTextFields.size() - 1 );
			}
			else {
				lastTF = extraPhoneNumbers.get(extraPhoneNumbers.size() - 1);
			}
			layout.putConstraint(SpringLayout.NORTH, addingPhoneButton, 5, SpringLayout.SOUTH , lastTF);
			layout.putConstraint(SpringLayout.NORTH, removePhoneButton, 5, SpringLayout.SOUTH , lastTF);
			layout.putConstraint(SpringLayout.SOUTH, content,5, SpringLayout.SOUTH, addingPhoneButton);
		}
		revalidate();
	}
	
	
	
	public DetailedPanel() {
		super(new BorderLayout());
		
		content.add(addingPhoneButton);
		layout.putConstraint(SpringLayout.SOUTH, content,5, SpringLayout.SOUTH, addingPhoneButton);
		
		content.add(removePhoneButton);
		layout.putConstraint(SpringLayout.EAST, removePhoneButton, 5, SpringLayout.EAST, content);
		
		
		for(int i = 0; i < labels.length; i++) {
			JLabel label = new JLabel(labels[i]);
			JTextField textField = new JTextField("", 20);
			jlabels.add(label);
			jTextFields.add(textField);
			
			content.add(label);
			content.add(textField);
			layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, content);
			layout.putConstraint(SpringLayout.WEST, textField, 70, SpringLayout.WEST, content);
			
			if (i == 0) {
			    layout.putConstraint(SpringLayout.NORTH, label,5, SpringLayout.NORTH, content);
			    layout.putConstraint(SpringLayout.NORTH, textField,5, SpringLayout.NORTH, content);
			} else {
				layout.putConstraint(SpringLayout.NORTH, label,5, SpringLayout.SOUTH, jTextFields.get(i-1));
			    layout.putConstraint(SpringLayout.NORTH, textField,5, SpringLayout.SOUTH, jTextFields.get(i-1));
			}
			
			
			if (i == labels.length - 1) {
			    layout.putConstraint(SpringLayout.NORTH, addingPhoneButton,5, SpringLayout.SOUTH, textField);
			    layout.putConstraint(SpringLayout.NORTH, removePhoneButton,5, SpringLayout.SOUTH, textField);
			}
			
		}
		
		saveButton = new SaveButton(this);
		newButton = new NewButton(this);
		deleteButton = new DeleteButton(this);
		newButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		newButton.setDeleteButton(deleteButton);
		saveButton.setDeleteButton(deleteButton);
		saveButton.setNewButton(newButton);
		deleteButton.setNewButton(newButton);
		JPanel modifyerButtons = new JPanel();
		
		this.add(BorderLayout.NORTH, content);
		this.add(BorderLayout.SOUTH, modifyerButtons);
		modifyerButtons.add(newButton);
		modifyerButtons.add(deleteButton);
		modifyerButtons.add(saveButton);
	}
	

	public void setContactJList(ContactJList cJList) {
		saveButton.setContactJList(cJList);
		newButton.setContactJList(cJList);
		deleteButton.setContactJList(cJList);
	}


	public void onContactSelected(Contact c) {
		jTextFields.get(0).setText(c.getFullName());
		jTextFields.get(1).setText(c.getNickname());
		jTextFields.get(2).setText(c.getAddress());
		jTextFields.get(3).setText(c.getEmail());
		String[] tempPhoneNumbers = c.getPhoneNumbers();
		jTextFields.get(4).setText(tempPhoneNumbers[0]);
		for (int i = 0; i < extraPhoneNumbers.size();) {
			removePhoneField();
		}
		if (tempPhoneNumbers.length > 1) {
			for (int i = 1; i < tempPhoneNumbers.length; i++) {
				addPhoneField();
				extraPhoneNumbers.get(i-1).setText(tempPhoneNumbers[i]);
			}
		}
		newButton.setEnabled(true);
		deleteButton.setEnabled(true);
	}
	

	
	public void disableDeleteAndNewButtons() {
		newButton.setEnabled(false);
		deleteButton.setEnabled(false);
	}
	

	public Contact getModifiedContact() {
		String modifiedFullName = jTextFields.get(0).getText();
		String modifiedNickname = jTextFields.get(1).getText();
		String modifiedAddress = jTextFields.get(2).getText();
		String modifiedEmail = jTextFields.get(3).getText();
		
		ArrayList<String> modifiedPhoneNumbers = new ArrayList<String>();
		modifiedPhoneNumbers.add(jTextFields.get(4).getText());
		for(int i = 0; i < extraPhoneNumbers.size(); i++) {
			modifiedPhoneNumbers.add(extraPhoneNumbers.get(i).getText());
		}
		Contact modifiedContact = new Contact(modifiedFullName, modifiedNickname, modifiedAddress, modifiedPhoneNumbers.toArray(new String[0]), modifiedEmail);
		
		return modifiedContact;
	}
	
	public void clearTextFields() {
		for (int i = 0; i < extraPhoneNumbers.size();) {
			removePhoneField();
		}
		for (int i = 0; i < jTextFields.size(); i++) {
			jTextFields.get(i).setText("");
		}
		
	}
	
	
}
