/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


public class Kupac extends Korisnik {

    private String cardNo;

    public Kupac() {
    }

    public Kupac(String userName, String userPass, String email, String phone, String name, String surrname, String cardNo) {
        super(userName, userPass, email, phone, name, surrname);
        this.cardNo = cardNo;
    }
    public Kupac(int id, String userName, String userPass, String email, String phone, String name, String surrname, String cardNo) {
        super(id,userName, userPass, email, phone, name, surrname);
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

}
