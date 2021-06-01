package com.reecetech.AddressBook.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.reecetech.AddressBook.model.Contact;

public class AddressBookServiceTest {

	private AddressBookService addressBookService;

	@Before
	public void init() {
		addressBookService = new AddressBookService();
	}

	@Test
	public void testAddContact() throws Exception {
		Contact contact = addressBookService.addContact("test", new Contact("name", 1234567));
		Contact expectedContact = new Contact("name", 1234567);
		Assert.assertTrue(expectedContact.equals(contact));
		addressBookService.removeContact("test", contact.getId());
	}

	@Test
	public void testRemoveContact() throws Exception {
		Contact contact = addressBookService.addContact("test", new Contact("name", 1234567));
		addressBookService.removeContact("test", contact.getId());
		try {
			addressBookService.removeContact("test", contact.getId());
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equals("Invalid contact ID"));
		}
	}

	@Test
	public void testRetrieveAllContactsFromAddressBook() throws Exception {
		Contact contact1 = addressBookService.addContact("test", new Contact("name", 1234567));
		Contact contact2 = addressBookService.addContact("test", new Contact("name", 1234567));
		List<Contact> actualContactList = addressBookService.retrieveContacts("test");
		List<Contact> expectedContactList = Arrays.asList(contact1, contact2);

		Assert.assertTrue(actualContactList.containsAll(expectedContactList));

	}

	@Test
	public void testAllUniqueContacts() throws Exception {

		Contact contact1 = addressBookService.addContact("test", new Contact("name", 1234567));
		Contact contact2 = addressBookService.addContact("test1", new Contact("name", 1234567));
		Contact contact3 = addressBookService.addContact("test", new Contact("DiffName", 1234567));

		List<Contact> contactList = addressBookService.retrieveAllUniqueContacts();

		Assert.assertTrue(contactList.size() == 2);

		addressBookService.removeContact("test", contact1.getId());
		addressBookService.removeContact("test1", contact2.getId());
		addressBookService.removeContact("test", contact3.getId());
	}

}
