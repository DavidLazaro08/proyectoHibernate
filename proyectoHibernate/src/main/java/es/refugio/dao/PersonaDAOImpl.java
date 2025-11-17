package es.refugio.dao;

import es.refugio.entities.Persona;
import es.refugio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonaDAOImpl implements PersonaDAO {

    @Override
    public List<Persona> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Persona> lista = s.createQuery("from Persona", Persona.class).list();
        s.close();
        return lista;
    }

    @Override
    public Persona findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Persona p = s.find(Persona.class, id);
        s.close();
        return p;
    }

    @Override
    public Persona findByDni(String dni) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Persona p = s.createQuery(
                        "from Persona where dni = :dni", Persona.class)
                .setParameter("dni", dni)
                .uniqueResult();

        s.close();
        return p;
    }

    @Override
    public Persona create(Persona persona) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.persist(persona);

        tx.commit();
        s.close();

        return persona;
    }

    @Override
    public Persona update(Persona persona) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.merge(persona);

        tx.commit();
        s.close();

        return persona;
    }

    @Override
    public boolean deleteById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Persona p = s.find(Persona.class, id);

        if (p != null) {
            s.remove(p);
            tx.commit();
            s.close();
            return true;
        }

        tx.commit();
        s.close();
        return false;
    }
}
