package GUIComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import LogicComponents.ContactManager;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("Telefonkönyv");
    	setSize(800,800);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JPanel headerPanel = new JPanel();
    	JLabel searchLabel = new JLabel("Név keresése:");
    	JTextField searchField = new JTextField(20);
    	SearchButton searchButton = new SearchButton();
    	headerPanel.add(searchLabel);
    	headerPanel.add(searchField);
    	headerPanel.add(searchButton);
    	
    	JPanel footerPanel = new JPanel();
    	JLabel sortLabel = new JLabel("Rendezés:");
    	ButtonGroup sortingButtons = new ButtonGroup();
    	SortingButton nameSortButton = new SortingButton("Név");
    	SortingButton emailSortButton = new SortingButton("E-mail");
    	sortingButtons.add(nameSortButton);
    	sortingButtons.add(emailSortButton);
    	footerPanel.add(sortLabel);
    	footerPanel.add(nameSortButton);
    	footerPanel.add(emailSortButton);
    	nameSortButton.setSelected(true);
    	
    	ContactManager contactManager = new ContactManager();
    	contactManager.setRadioButton(nameSortButton);
    	DetailedPanel detailedPanel = new DetailedPanel();
    	ContactJList contactJList = new ContactJList(contactManager, detailedPanel);
    	detailedPanel.setContactJList(contactJList);
    	
    	nameSortButton.setContactManager(contactManager);
    	emailSortButton.setContactManager(contactManager);
    	nameSortButton.setContactJList(contactJList);
    	emailSortButton.setContactJList(contactJList);
    	searchButton.setContactManager(contactManager);
    	searchButton.setContactJList(contactJList);
    	searchButton.setTextField(searchField);
    	
    	JScrollPane scrollPanel = new JScrollPane(contactJList);
    	JSplitPane bodyPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPanel, detailedPanel);
    	bodyPanel.setOneTouchExpandable(true);
    	bodyPanel.setDividerLocation(400);
    	Dimension minimumSize = new Dimension(100, 100);
    	scrollPanel.setMinimumSize(minimumSize);
    	detailedPanel.setMinimumSize(minimumSize);
    	
    	getContentPane().add(BorderLayout.NORTH, headerPanel);
    	getContentPane().add(BorderLayout.SOUTH, footerPanel);
    	getContentPane().add(BorderLayout.CENTER, bodyPanel);
    	
    	setVisible(true);
	}
	
	
	
}
