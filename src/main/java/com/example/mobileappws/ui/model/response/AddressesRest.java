package com.example.mobileappws.ui.model.response;

import org.springframework.hateoas.RepresentationModel;

//public class AddressesRest extends RepresentationModel<AddressesRest> {
public class AddressesRest extends RepresentationModel<AddressesRest> {
    private String city;
    private String Country;
    private String addressId;
//    private String addressID;
    private String StreetName;
    private String PostalCode;
    private String Type;

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

//    public String getAddressID() {
//        return addressID;
//    }
//
//    public void setAddressID(String addressID) {
//        this.addressID = addressID;
//    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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
}
