/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package My_Classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
/**
 *
 * @author Andy
 */
public class Genre {
    private int id;
    private String name;

    public void setId(int _id) {
        this.id = _id;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Genre(){};
    
    public Genre(int _id ,String _name)
    {
    this.id=_id;
    this.name=_name;
    }
    //functions
    
    public void addGenre(String _name)
    {
        String insertQuery ="INSERT INTO `book_genres`(`name`) VALUES (?)";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _name);
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Genre Added","add genre",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Genre Not Added","add genre",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
     public void editGenre(int _id,String _name)
    {
        String editQuery ="UPDATE `book_genres` SET `name` = ? WHERE `id` = ?";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _name);
            ps.setInt(2, _id);
            
            
           
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Genre Edited","edit genre",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Genre Not Edited","edit genre",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
     
     public void removeGenre(int _id)
    {
        String removeQuery ="DELETE FROM `book_genres` WHERE `id` = ?";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(removeQuery);
           ;
            ps.setInt(1, _id);
            
            
           
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Genre Deleted","Remove",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Genre Not Detected","removed",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
    }
    
     public ArrayList<Genre> genreList()
     {
         ArrayList<Genre> gList = new ArrayList<>();
          
         My_Classes.Func_Class func = new Func_Class();
         
     
         
         
        try {
            
            
           ResultSet rs=func.getData("SELECT * FROM `book_genres`");
            
            Genre genre;
            while(rs.next())
            {
                genre = new Genre(rs.getInt("id"), rs.getString("name"));
                gList.add(genre);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return gList; 
     }
     
     
}
