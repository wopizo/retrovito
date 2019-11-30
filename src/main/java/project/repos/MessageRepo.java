package project.repos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import project.domain.Message;

import java.util.List;

public class MessageRepo {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Message> getAllMessages(){
        Session session = sessionFactory.openSession();
        List<Message> messages = null;
        try{
            session.beginTransaction();

            messages = session.createSQLQuery("SELECT * FROM retrovito.message")
                    .addEntity(Message.class)
                    .list();

            System.out.println(messages.get(0).getMessage());
            System.out.println(messages.get(1).getMessage());

            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
            sessionFactory.close();
        }

        return messages;
    }

    public MessageRepo() {
    }
}
