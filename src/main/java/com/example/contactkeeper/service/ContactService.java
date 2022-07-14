package com.example.contactkeeper.service;

import com.example.contactkeeper.exceptions.ApiException;
import com.example.contactkeeper.model.Contact;
import com.example.contactkeeper.model.MyUser;
import com.example.contactkeeper.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public void addContact(MyUser myUser, Contact contact) {
        contact.setMyUser(myUser);
        contactRepository.save(contact);
    }

    public List<Contact> getContactsByUserId(String userId) {
        List<Contact> contacts= contactRepository.findAllByMyUser(userId);
        return contacts;
    }


    public void updateContact(String userId, String contactId, Contact contact) {
        Contact updateContact=contactRepository.findById(contactId).orElseThrow(()-> new ApiException("There is not contact with this id"));
        if (!updateContact.getMyUser().getId().equals(userId)){
            throw new ApiException("You don't own this contact to update it !");
        }
        updateContact.setName(contact.getName());
        updateContact.setPhoneNumber(contact.getPhoneNumber());
        contactRepository.save(updateContact);
    }


    public void deleteContact(String userId, String contactId) {
        Contact deleteContact = contactRepository.findById(contactId).orElseThrow(()-> new ApiException("There is not contact with this id"));
        if (!deleteContact.getMyUser().getId().equals(userId)){
            throw new ApiException("You don't own this contact to deleted it !");
        }
        contactRepository.delete(deleteContact);
    }


}
