package org.example.domain;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private String firstname;
    private String surname;
    private int phone;
    private String email;
    private final UUID uuid;

    public Customer (String firstname, String surname, int phone, String email){
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.uuid = UUID.randomUUID();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone){
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public UUID getUuid(){
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(uuid, customer.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, surname, phone, email, uuid);
    }
}
