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
public class Member {
    
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String gender;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Member(){}
    
    public Member(int _id,String _fname,String _lname,String _phone ,String _email,String _gender)
            
    {
        this.id= _id;
        this.firstName=_fname;
        this.lastName=_lname;
        this.phone=_phone;
        this.email=_email;
        this.gender=_gender;
                 
    }
    
  public void addMember(String _fname , String _lname, String _phone,String _email,String _gender)
    {
        String insertQuery ="INSERT INTO `members`(`firstName`, `lastName`, `phone`, `email`, `gender`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _phone);
            ps.setString(4, _email);
            ps.setString(5, _gender);
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Member Added","add member",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Member Not Added","add member",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  
  public void editMember(Integer _id ,String _fname , String _lname, String _phone,String _email,String _gender)
    {
        String editQuery ="UPDATE `members` SET `firstName`=?,`lastName`=?,`phone`=?,`email`=?,`gender`=? WHERE `id` =?";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(editQuery);
          ps.setString(1, _fname);
            ps.setString(2, _lname);
            ps.setString(3, _phone);
            ps.setString(4, _email);
            ps.setString(5, _gender);
            ps.setInt(6, _id);
           
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Member Edited","edit member",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Member Not Edited","edit member",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
  
  
   public void removeMember(int _id)
    {
        String removeQuery ="DELETE FROM `members` WHERE `id` = ?";
        try {
            PreparedStatement ps =DB.getConnection().prepareStatement(removeQuery);
           ;
            ps.setInt(1, _id);
            
            
           
            if(ps.executeUpdate() !=0)
            {
                JOptionPane.showConfirmDialog(null, "Member Deleted","Remove",1);
            }
             else
            {
                JOptionPane.showConfirmDialog(null, "Member Not Detected","removed",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
   public Member getMemberById(Integer _id) throws SQLException
   {
       Func_Class func =new Func_Class();
        String query ="SELECT * FROM `members` WHERE `id` =?"+_id;
   ResultSet rs =func.getData(query);
   
   if (rs.next())
   {
    return new Member(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)) ;  
   }    
   else{ return null;
}
   }
   
   public ArrayList<Member> memberList()
     {
         ArrayList<Member> mList = new ArrayList<>();
         
         My_Classes.Func_Class func = new Func_Class();
       
         
        try {
            
            
            
             ResultSet rs=func.getData("SELECT * FROM `members`");
            
            Member member;
            while(rs.next())
            {
               
                member = new Member(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("phone"), rs.getString("email"),rs.getString("gender"));
                mList.add(member);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mList; 
     }
    


}

