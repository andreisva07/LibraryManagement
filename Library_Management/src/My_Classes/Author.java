/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package My_Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Andy
 */
public class Author {
    
    private int id;
    private String firstName;
    private String lastName;
    private String field_of_Expertise;
    private String about;

    public Author(){}
    
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setField_of_Expertise(String field_of_Expertise) {
        this.field_of_Expertise = field_of_Expertise;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getField_of_Expertise() {
        return field_of_Expertise;
    }

    public String getAbout() {
        return about;
    }
    
    public Author(int _id,String _fname,String _lname,String _expertise ,String _about)
            
    {
        this.id= _id;
        this.firstName=_fname;
        this.lastName=_lname;
        this.field_of_Expertise=_expertise;
        this.about=_about;
                 
    }
    public void addAuthor(String _fname , String _lname, String _expertise,String _about)
    {
        String insertQuery ="INSERT INTO `authors`(`firstName`, `lastName`, `expertise`, `about`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _expertise);
            ps.setString(4, _about);
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Author Added","add author",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Author Not Added","add author",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
    
    public void editAuthor(int _id,String _fname , String _lname, String _expertise,String _about)
    {
        String editQuery ="UPDATE `authors` SET `firstName`=?,`lastName`=?,`expertise`=?,`about`=? WHERE `id` = ?";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(editQuery);
           ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _expertise);
            ps.setString(4, _about);
            ps.setInt(5, _id);
           
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Author Edited","edit author",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Author Not Edited","edit author",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
        public void removeAuthor(int _id)
    {
        String removeQuery ="DELETE FROM `authors` WHERE `id` = ?";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(removeQuery);
           ;
            ps.setInt(1, _id);
            
            
           
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Author Deleted","Remove",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Author Not Detected","removed",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
        
        
    }
    
        public ArrayList<Author> authorsList()
     {
         ArrayList<Author> aList = new ArrayList<>();
         
         My_Classes.Func_Class func = new Func_Class();
       
         
        try {
            
            
            
             ResultSet rs=func.getData("SELECT * FROM `authors`");
            
            Author author;
            while(rs.next())
            {
               
                author = new Author(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("expertise"), rs.getString("about"));
                aList.add(author);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Author.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aList; 
     }
    
}

