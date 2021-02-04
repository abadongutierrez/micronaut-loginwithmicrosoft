package com.jabaddon.tutorials.micronaut.loginwithmicrosoft;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class MicrosoftUser {
    private String id;
    private String surname;
    private String givenName;
    private String userPrincipalName;

    public MicrosoftUser() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    @Override
    public String toString() {
        return "MicrosoftUser{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", givenName='" + givenName + '\'' +
                ", userPrincipalName='" + userPrincipalName + '\'' +
                '}';
    }
}
