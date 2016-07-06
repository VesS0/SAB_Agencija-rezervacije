/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author vlada
 */
public class Adresa {

    private String state;
    private String city;
    private String street;
    private String number;

    public Adresa() {
    }

    public Adresa(String state, String city, String street, String number) {
        this.state = state.trim();
        this.city = city.trim();
        this.street = street.trim();
        this.number = number.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number.trim();
    }

}
