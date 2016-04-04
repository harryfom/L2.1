package accounts.executor;

import accounts.dao.UserDAO;
import accounts.dataSets.UserProfile;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AccountService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl = "update";

    private final SessionFactory sessionFactory;

    public AccountService() {
        Configuration configuration = getH2Config();
        sessionFactory = createSessionFactory(configuration);
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getH2Config() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(UserProfile.class);
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        config.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        config.setProperty("hibernate.connection.username", "test");
        config.setProperty("hibernate.connection.password", "test");
        config.setProperty("hibernate.show_sql", hibernate_show_sql);
        config.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl);
        return config;
    }

    public long addNewUser(UserProfile userProfile) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDAO dao = new UserDAO(session);
            long userId = dao.addUser(userProfile);
            transaction.commit();
            session.close();
            return userId;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public UserProfile getUserByLogin(String login) {
        try {
            Session session = sessionFactory.openSession();
            UserDAO dao = new UserDAO(session);
            UserProfile userProfile = dao.getByLogin(login);
            session.close();
            return userProfile;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public List<UserProfile> getAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            UserDAO dao = new UserDAO(session);
            List<UserProfile> userProfiles = dao.getAllUsers();
            session.close();
            return userProfiles;
        } catch (HibernateException e) {
            throw new DBException(e);
        }

    }

}
