package main;

import model.Persona;
import model.Telefono;
import persistent.PersonaDao;

public class Main {
    public static void main(String args[]) {
        PersonaDao dao = new PersonaDao();
        var p = dao.personaPorId(1);
        System.out.println(p.nombre());
        for (Telefono telefono : p.telefonos()) {
            System.out.println(telefono);
        }
    }
}
