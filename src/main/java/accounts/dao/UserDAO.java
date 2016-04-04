package accounts.dao;

import accounts.dataSets.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by dns on 03.04.2016.
 */
public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserProfile getByLogin(String name){
        Criteria cr = session.createCriteria(UserProfile.class);
        return (UserProfile) cr.add(Restrictions.eq("login",name)).uniqueResult();
    }

    public long addUser(UserProfile userProfile) {
        return (Long) session.save(userProfile);
    }

    public List<UserProfile> getAllUsers(){
        return session.createCriteria(UserProfile.class).list();
    }
}
