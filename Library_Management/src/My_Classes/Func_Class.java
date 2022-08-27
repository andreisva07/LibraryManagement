/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package My_Classes;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Andy
 */
public class Func_Class {
     public void displayImage(int width,int height,String imagePath,JLabel label)
  {
     ImageIcon imgIco = new ImageIcon(getClass().getResource(imagePath));
      Image image = imgIco.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
      label.setIcon(new ImageIcon(image));
  
   
  }
     
     public void customTable(JTable table)
     {
         
     }
     
     
     public ResultSet getData(String query)
     {
         PreparedStatement ps;
         ResultSet rs=null;
         
        try {
            
            
            ps = DB.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
        }
        catch (SQLException ex) {
            Logger.getLogger(Func_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
     }
    
}
