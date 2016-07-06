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
public class Korisnik {

    private int userID;
    private String userName;
    private String userPass;
    private String email;
    private String phone;
    private String name;
    private String surrname;

    public Korisnik() {
    }

    public Korisnik(int userID, String userName, String userPass, String email, String phone, String name, String surrname) {
        this.userID = userID;
        this.userName = userName.trim();
        this.userPass = userPass.trim();
        this.email = email.trim();
        this.phone = phone.trim();
        this.name = name.trim();
        this.surrname = surrname.trim();
    }
        public Korisnik(String userName, String userPass, String email, String phone, String name, String surrname) {
        this.userName = userName.trim();
        this.userPass = userPass.trim();
        this.email = email.trim();
        this.phone = phone.trim();
        this.name = name.trim();
        this.surrname = surrname.trim();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getSurrname() {
        return surrname;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname.trim();
    }

}
