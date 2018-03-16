package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public String uid;
    public int business_number;
    public String name;
    public String primary_business;
    public String address;
    public String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Contact constructor that allow us to create contact with the following fields.
     *
     * @param business_number
     * @param uid
     * @param name
     * @param primary_business
     * @param address
     * @param province
     */
    public Contact(int business_number, String uid, String name, String primary_business, String address, String province) {
        this.business_number = business_number;
        this.uid = uid;
        this.name = name;
        this.primary_business = primary_business;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("business_number", business_number);
        result.put("name", name);
        result.put("primary_business", primary_business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
