/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Apartman;
import entity.Korisnik;

/**
 *
 * @author vlada
 */
public class MySession {

    private static MySession session;
    private Korisnik sessionUser;
   
    public MySession() {
    }

    public static synchronized MySession getSessionInstance() {
        if (session == null) {
            session = new MySession();
        }
        return session;
    }

    public Korisnik getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(Korisnik sessionUser) {
        this.sessionUser = sessionUser;
    }


}
