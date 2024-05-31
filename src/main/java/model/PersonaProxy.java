package model;

import persistent.PersonaDao;

import java.util.HashSet;
import java.util.Set;

public class PersonaProxy implements IPersona{
    private boolean isTelefonosCargados;
    private IPersona persona;
    private int idPersona;
    private PersonaDao personaDao;

    public PersonaProxy(int idPersona, Persona persona, PersonaDao personaDao) {
        this.isTelefonosCargados = false;
        this.idPersona = idPersona;
        this.persona = persona;
        this.personaDao = personaDao;
    }

    @Override
    public Telefono[] telefonos() {
        if (!this.isTelefonosCargados){
            this.persona = personaDao.telefonosPorIdPersona(this.idPersona);
            this.persona.telefonos();
            this.isTelefonosCargados = true;
        }
        return this.persona.telefonos();
    }

    @Override
    public String nombre() {
        return persona.nombre();
    }
}
