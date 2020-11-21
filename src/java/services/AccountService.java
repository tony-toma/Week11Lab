package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
				// Log activity
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
               
                // send basic email
                /*
                String msgBody = "Your account has logged in";
                GmailService.sendMail(email, "Youre account has logged in", msgBody, false);
                */
                
                // Send E-mail		
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                GmailService.sendMail(to, subject, template, tags);
				
                
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public boolean resetPassword(String email, String path, String url) {
        UserDB userDB = new UserDB();
        String uuid = UUID.randomUUID().toString();
        User user =  userDB.get(email);
        user.setResetpasswordUUID(uuid);
        userDB.update(user);
        
        url += "?uuid=" + uuid;
        
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("firstname", user.getFirstName());
        map.put("lastname", user.getLastName());
        map.put("username", user.getEmail());
        map.put("link", url);
     
        
        try {
            GmailService.sendMail(email, "Reset Password", path, map);
        }
        catch(Exception e) {
        }
        
        
                
       return true;
    }
    
    
   public boolean changePassword(String uuid, String password) {
       UserDB userDB = new UserDB();
       
       try {
           User user = userDB.getUserByUUID(uuid);
           user.setPassword(password);
           user.setResetpasswordUUID(null);
           userDB.update(user);
           return true;
           
       }
       catch(Exception e) {
       }

       
       
       return false;
   }
    
  
}
