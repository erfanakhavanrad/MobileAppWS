package com.example.mobileappws.service;

import com.example.mobileappws.shared.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddresses(String userId);

    AddressDTO getAddress(String addressId);
}
