package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmRunCandidate {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate can1 = Candidate.of("Glen", 5, 30000);
            Candidate can2 = Candidate.of("Olaf", 3, 20000);
            Candidate can3 = Candidate.of("Ulrich", 1, 10000);

            session.save(can1);
            session.save(can2);
            session.save(can3);

            List<Candidate> list = session.createQuery("from Candidate ").list();
            for (Candidate can : list) {
                System.out.println(can);
            }

            Candidate candidate = (Candidate) session.createQuery("from Candidate where id = :id")
                    .setParameter("id", 2).uniqueResult();
            System.out.println(candidate);

            Candidate candidate1 = (Candidate) session.createQuery("from Candidate where name=:name")
                    .setParameter("name", "Glen").uniqueResult();
            System.out.println(candidate1);

            session.createQuery(
            "update Candidate c set c.name =:name, c.salary=:salary, c.experience=:exp where c.id=:id")
                    .setParameter("name", "Thor")
                    .setParameter("salary", 50000)
                    .setParameter("exp", 2000)
                    .setParameter("id", 3)
                    .executeUpdate();

            session.createQuery("delete from Candidate where id=:id")
                    .setParameter("id", 2)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
