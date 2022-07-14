package com.example.contactkeeper.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Contact {
    @Id
    private String id = UUID.randomUUID().toString().toUpperCase();
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Phone Number is required")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private MyUser myUser;
}
