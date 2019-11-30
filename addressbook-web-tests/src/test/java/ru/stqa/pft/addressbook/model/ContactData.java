package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String mobilephone;
    private final String email;
    private String group;

    public ContactData(String firstname, String lastname, String mobilephone, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobilephone = mobilephone;
        this.email = email;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
