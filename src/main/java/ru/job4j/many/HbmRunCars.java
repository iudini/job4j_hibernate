//package ru.job4j.many;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
//public class HbmRunCars {
//    public static void main(String[] args) {
//    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//    try {
//        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        Session session = sf.openSession();
//        session.beginTransaction();
//
//        Model model1 = Model.of("A class");
//        Model model2 = Model.of("B class");
//        Model model3 = Model.of("C class");
//        Model model4 = Model.of("E class");
//        Model model5 = Model.of("S class");
//        session.save(model1);
//        session.save(model2);
//        session.save(model3);
//        session.save(model4);
//        session.save(model5);
//
//        Mark mark = Mark.of("Mercedes");
//        mark.addModel(session.load(Model.class, 1));
//        mark.addModel(session.load(Model.class, 2));
//        mark.addModel(session.load(Model.class, 3));
//        mark.addModel(session.load(Model.class, 4));
//        mark.addModel(session.load(Model.class, 5));
//        session.save(mark);
//
//        session.getTransaction().commit();
//        session.close();
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        StandardServiceRegistryBuilder.destroy(registry);
//    }
//    }
//}
