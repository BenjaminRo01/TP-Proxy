package model;

import persistent.PersonaDao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProxyTelefonos  implements Set<Telefono> {
    private int idPersona;
    private PersonaDao personaDao;
    private Persona persona;
    private Set<Telefono> telefonos;

    public ProxyTelefonos(int idPersona, PersonaDao personaDao) {
        this.idPersona = idPersona;
        this.personaDao = personaDao;
        this.telefonos = new HashSet<>();
    }

    private void cargarTelefonos(){
        if (telefonos.isEmpty()){
            this.telefonos = personaDao.telefonosPorIdPersona(this.idPersona);
            this.persona = new Persona(idPersona, null, this.telefonos);
            this.persona.telefonos();
        }
    }

    @Override
    public int size() {
        return telefonos.size();
    }

    @Override
    public boolean isEmpty() {
        return telefonos.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return telefonos.contains(o);
    }

    @Override
    public Iterator<Telefono> iterator() {
        return telefonos.iterator();
    }

    @Override
    public Object[] toArray() {
        return telefonos.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        cargarTelefonos();
        return telefonos.toArray(a);
    }

    @Override
    public boolean add(Telefono telefono) {
        return telefonos.add(telefono);
    }

    @Override
    public boolean remove(Object o) {
        return telefonos.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return telefonos.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
        return telefonos.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return telefonos.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return telefonos.removeAll(c);
    }

    @Override
    public void clear() {
        telefonos.clear();
    }
}
