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
public class Prodavac extends Korisnik {

    private String POS;

    public Prodavac() {
    }

    public Prodavac(String userName, String userPass, String email, String phone, String name, String surrname, String POS) {
        super(userName, userPass, email, phone, name, surrname);
        this.POS = POS;
    }
    
        public Prodavac(int id,String userName, String userPass, String email, String phone, String name, String surrname, String POS) {
        super(id,userName, userPass, email, phone, name, surrname);
        this.POS = POS;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public String getPOS() {
        return POS;
    }

}
