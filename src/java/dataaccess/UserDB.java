package dataaccess;

import java.util.logging.Logger;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public boolean update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try {
            et.begin();
            em.merge(user);
            et.commit();
        }
        catch(Exception e) {
            et.rollback();
        }
        finally {
            em.close();
        }
        
        return true;
    }
    
    
    
    
    public User getUserByUUID(String uuid) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.createNamedQuery("User.findByResetpasswordUUID", User.class)
                    .setParameter("resetpasswordUUID", uuid).getSingleResult();
            return user;
        }
        catch(Exception e) {
            
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", e);
            throw new Exception("Error getting users");
        }
        finally {
            em.close();
        }
    }
    
}
