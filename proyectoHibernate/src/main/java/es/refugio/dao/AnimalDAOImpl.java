package es.refugio.dao;

import es.refugio.entities.Animal;
import es.refugio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    @Override
    public List<Animal> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Animal> lista = s.createQuery("from Animal", Animal.class).list();
        s.close();
        return lista;
    }

    @Override
    public Animal findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Animal a = s.find(Animal.class, id);
        s.close();
        return a;
    }

    @Override
    public List<Animal> findByEspecie(String especie) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Animal> lista = s.createQuery(
                        "from Animal where especie = :especie", Animal.class)
                .setParameter("especie", especie)
                .list();

        s.close();
        return lista;
    }

    @Override
    public List<Animal> findByDuenio(Long personaId) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Animal> lista = s.createQuery(
                        "from Animal where duenio.id = :id", Animal.class)
                .setParameter("id", personaId)
                .list();

        s.close();
        return lista;
    }

    @Override
    public Animal create(Animal animal) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.persist(animal);

        tx.commit();
        s.close();

        return animal;
    }

    @Override
    public Animal update(Animal animal) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.merge(animal);

        tx.commit();
        s.close();

        return animal;
    }

    @Override
    public boolean deleteById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Animal a = s.find(Animal.class, id);

        if (a != null) {
            s.remove(a);
            tx.commit();
            s.close();
            return true;
        }

        tx.commit();
        s.close();
        return false;
    }
}
