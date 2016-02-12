import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.swing.*;
import java.awt.event.*;

public class SwingMD5 extends JFrame
{
   private String md5Hash;
   private String input;
   
   public SwingMD5() {
      initUI();
   }
   
   private void initUI() {
      JPanel jp = new JPanel();
      JTextArea jt = new JTextArea("Input here",3,20);
      
      input = jt.getText();      
      md5Hash = hashingAlg(input);
            
      String [] initArr = {"MD5 input has " + jt.getText().length() + " characters","MD5: " + md5Hash};
      JList jl = new JList(initArr);
      
      setVisible(true);
      setTitle("MD5 For String");
      setSize(600,120);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      JButton jb = new JButton(">>>");
      
      jb.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            input = jt.getText();
            md5Hash = hashingAlg(input);
            String [] newArr = {"MD5 input has " + jt.getText().length() + " characters","MD5: " + md5Hash};
            jl.setListData(newArr);
            jp.add(jl);
         }
      });
      
      jp.add(jt);
      jp.add(jb);
      jp.add(jl);
      add(jp);   
   }
   
   String hashingAlg(String s) {
      String md5Hash = "";
      
      try {
         MessageDigest md = java.security.MessageDigest.getInstance("MD5");
         byte[] arr = md.digest(s.getBytes());      // result here
         String[] sArr = new String[16];
            
         for (int i = 0; i < arr.length; i++) {       // Convert byte array to string array
             sArr[i] = String.format("%02x",arr[i]);
         }
               
         for (int j = 0; j < sArr.length; j++) {      // Create string from array
             md5Hash+=sArr[j];
         }
      } catch (NoSuchAlgorithmException exception) {
         System.out.println("Sorry, cryptographic algorithm unavailable");
      }
      
      return md5Hash;
   }
   
   public static void main(String [] args) {
      SwingMD5 test = new SwingMD5();
   }
}