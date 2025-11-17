package es.refugio.dao;

import es.refugio.entities.Clasificacion;
import es.refugio.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClasificacionDAOImpl implements ClasificacionDAO {

    @Override
    public List<Clasificacion> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Clasificacion> lista = s.createQuery("from Clasificacion", Clasificacion.class).list();
        s.close();
        return lista;
    }

    @Override
    public Clasificacion findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Clasificacion c = s.find(Clasificacion.class, id);
        s.close();
        return c;
    }

    @Override
    public Clasificacion findByCodigo(String codigo) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Clasificacion c = s.createQuery(
                        "from Clasificacion where codigo = :codigo", Clasificacion.class)
                .setParameter("codigo", codigo)
                .uniqueResult();

        s.close();
        return c;
    }

    @Override
    public Clasificacion create(Clasificacion clasificacion) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.persist(clasificacion);

        tx.commit();
        s.close();

        return clasificacion;
    }

    @Override
    public Clasificacion update(Clasificacion clasificacion) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.merge(clasificacion);

        tx.commit();
        s.close();

        return clasificacion;
    }

    @Override
    public boolean deleteById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        Clasificacion c = s.find(Clasificacion.class, id);

        if (c != null) {
            s.remove(c);
            tx.commit();
            s.close();
            return true;
        }

        tx.commit();
        s.close();
        return false;
    }
}
