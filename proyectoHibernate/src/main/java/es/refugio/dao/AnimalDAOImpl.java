package es.refugio.dao;

import es.refugio.entities.Animal;
import es.refugio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    @Override
    public List<Animal> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animal> lista = session.createQuery("from Animal", Animal.class).list();
        session.close();
        return lista;
    }

    @Override
    public Animal findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Animal animal = session.find(Animal.class, id);
        session.close();
        return animal;
    }

    @Override
    public List<Animal> findByEspecie(String especie) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animal> lista = session.createQuery(
                        "from Animal where especie = :especie", Animal.class)
                .setParameter("especie", especie)
                .list();
        session.close();
        return lista;
    }

    @Override
    public Animal create(Animal animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(animal);
        tx.commit();
        session.close();
        return animal;
    }

    @Override
    public Animal update(Animal animal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(animal);
        tx.commit();
        session.close();
        return animal;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Animal animal = session.find(Animal.class, id);
        if (animal != null) {
            session.remove(animal);
            tx.commit();
            session.close();
            return true;
        }
        tx.commit();
        session.close();
        return false;
    }
}
