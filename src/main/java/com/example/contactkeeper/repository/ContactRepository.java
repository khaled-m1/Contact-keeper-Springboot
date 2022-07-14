package com.example.contactkeeper.repository;

import com.example.contactkeeper.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    @Query("select c from Contact c where c.myUser.id=?1")
    List<Contact> findAllByMyUser(String userId);

}
