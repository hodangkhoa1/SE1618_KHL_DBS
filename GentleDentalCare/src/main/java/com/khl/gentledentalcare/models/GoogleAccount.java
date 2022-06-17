package com.khl.gentledentalcare.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ASUS
 */
@Getter
@Setter
public class GoogleAccount {

    private String id, email, name, given_name, family_name, link, picture;
    private boolean verified_email;

    public GoogleAccount(String id, String email, String name, String given_name, String family_name, String link, String picture, boolean verified_email) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.link = link;
        this.picture = picture;
        this.verified_email = verified_email;
    }
}
