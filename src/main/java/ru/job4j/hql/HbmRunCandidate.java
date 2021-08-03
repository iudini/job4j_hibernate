package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunCandidate {
    public static void main(String[] args) {
        Candidate rsl = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            rsl = (Candidate) session.createQuery(
                    "select c from Candidate c " +
                            "join fetch c.vacancyBase vb " +
                            "join fetch vb.vacancies v " +
                            "where c.id=:cId"
            )
                    .setParameter("cId", 1)
                    .uniqueResult();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        System.out.println(rsl);
    }
}
