package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")

public class ContactData {
    @Expose
@Id
@Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilephone;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homephone;
    @Transient
    private String allEmails;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workphone;
    @Expose
    @Type(type = "text")
    private String address;
    @Expose
    @Type(type = "text")
    private String email;
    @Expose
    @Type(type = "text")
    private String email2;
    @Expose
    @Type(type = "text")
    private String email3;
    @Transient
    @Column(name= "photo")
    @Type(type = "text")
    private String  photo;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
           inverseJoinColumns =@JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobilephone, that.mobilephone) &&
                Objects.equals(homephone, that.homephone) &&
                Objects.equals(workphone, that.workphone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobilephone, homephone, workphone, address, email, email2, email3);
    }


    public File getPhoto() {
        return new File (photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;


    }

    public int getId() {
        return id;
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

    public Groups getGroups() {
        return new Groups( groups);
    }

    public String getAddress() {
        return address;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getWorkphone() {
        return workphone;
    }

    public String getAllEmails() {
        return allEmails;
    }


    public String getHomephone() {
        return homephone;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withWorkphone(String workphone) {
        this.workphone = workphone;
        return this;
    }

    public ContactData withHomephone(String homephone) {
        this.homephone = homephone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }


    public ContactData withAddress(String address) {
        this.address = address;
        return this;

    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;

    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", homephone='" + homephone + '\'' +
                ", workphone='" + workphone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                '}';
    }

    public ContactData inGroup(GroupData group){
        groups.add(group);
        return this;
    }
}
