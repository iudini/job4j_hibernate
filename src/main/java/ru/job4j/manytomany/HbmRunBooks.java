package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunBooks {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book one = Book.of("Alifba");
            Book two = Book.of("Math");
            Book three = Book.of("Book");

            Author author1 = Author.of("John");
            Author author2 = Author.of("Robert");
            Author author3 = Author.of("Martin");

            one.getAuthors().add(author1);
            one.getAuthors().add(author2);
            two.getAuthors().add(author3);
            three.getAuthors().add(author1);
            three.getAuthors().add(author2);
            three.getAuthors().add(author3);

            session.persist(one);
            session.persist(two);
            session.persist(three);

            Book book = session.get(Book.class, 1);
            session.remove(book);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
