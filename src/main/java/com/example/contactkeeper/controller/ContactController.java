package com.example.contactkeeper.controller;

import com.example.contactkeeper.DTO.Api;
import com.example.contactkeeper.model.Contact;
import com.example.contactkeeper.model.MyUser;
import com.example.contactkeeper.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getContactsByUserId(myUser.getId()));
    }

    @PostMapping
    public ResponseEntity<Api> addContact(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Contact contact){
        contactService.addContact(myUser,contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("New Contact added",201));
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Api> updateContact(@AuthenticationPrincipal MyUser myUser, @PathVariable String contactId, @RequestBody @Valid Contact contact){
        contactService.updateContact(myUser.getId(),contactId,contact);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Contact update :) ",200));
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Api> deleteContact(@AuthenticationPrincipal MyUser myUser,@PathVariable String contactId){
        contactService.deleteContact(myUser.getId(),contactId);
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Contact deleted ",200));
    }


}
