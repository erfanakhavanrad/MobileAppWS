package com.example.mobileappws.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 8347655699530045914L;
    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private String addressID;

    @Column()
    private String city;

    @Column()
    private String Country;

    @Column()
    private String StreetName;

    @Column()
    private String PostalCode;

    @Column()
    private String Type;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity userDetails;

    public Long getId
            () {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressID() {
        return addressID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }
}
