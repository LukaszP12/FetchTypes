package Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CloseSessionApp {

    public static void main(String[] args) {
        // stworzyc obiekt Configuration
        Configuration conf = new Configuration().configure("hibernate.cfg.xml");
        // wczytanie adnotacji
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        conf.addAnnotatedClass(Property.class);

        // stworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        // pobranie sesji
        Session session = factory.getCurrentSession();

        int id =36;

        session.beginTransaction();

        System.out.println("Pobieranie obiektu company");
        Company company = session.get(Company.class, id); //properties nie zostało pobrane tutaj, ale dopiero w liniejce 36, bo jest FetchType.LAZY
        System.out.println("Pobrano obiekt company");
        System.out.println(company);

        session.getTransaction().commit();

        System.out.println("...");

        System.out.println("Nieruchomości");
        for (Property property : company.getProperties()){
            System.out.println(property);
        }

        //zamknięcie obiektu SessionFactory
        factory.close();
    }

}
