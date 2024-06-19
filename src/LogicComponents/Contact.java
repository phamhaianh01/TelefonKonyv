package LogicComponents;

import java.io.*;

public class Contact implements Serializable, Comparable{
	private String fullName;
	private String nickname;
	private String address;
	private String[] phoneNumbers;
	private String email;
	private Boolean compareByName = true;
	
	public Contact(String fN, String nn, String a, String[] pNs, String e) {
		fullName = fN;
		nickname = nn;
		address = a;
		phoneNumbers = pNs;
		email = e;
	}
	/**
	 * 
	 * @return
	 */
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCompareByName(Boolean compareByName) {
		this.compareByName = compareByName;
	}

	@Override
	public int compareTo(Object o) {
		Contact c = (Contact)o;
		if (compareByName) {
			return this.getFullName().compareTo(c.getFullName());
		}
		else 
			return this.getEmail().compareTo(c.getEmail());
	}
	
	
	
	
}
