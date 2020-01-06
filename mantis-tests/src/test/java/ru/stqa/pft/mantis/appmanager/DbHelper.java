package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;

import java.util.HashSet;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper(){
// A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();


}
    public HashSet<UserData> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List <GroupData> result = session.createQuery( "from GroupData " ).list();
        List<UserData> result = session.createQuery( "from UserData ").list();
        // for ( GroupData group :  result ) {
        //for ( ContactData contact :  result ) {
        //System.out.println(group);
        //System.out.println(contact);}
        session.getTransaction().commit();
        session.close();
        return new HashSet<UserData>(result) {
        };
    }
}


