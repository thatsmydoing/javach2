package edu.ateneo.other;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dump {
  public static void main(String[] args) throws Exception {
    Class.forName("org.sqlite.JDBC");
    Connection conn = DriverManager.getConnection("jdbc:sqlite:/media/ACER/Optional/xampplite/htdocs/bluej3/protected/data/production.db");
    PreparedStatement ps = conn.prepareStatement("SELECT fileName, fileContents, label FROM NonLiteralError n JOIN CompileSessionEntry c ON n.compileSessionEntryId=c.id");
    ResultSet rs = ps.executeQuery();
    while(rs.next()) {
      String fileName = rs.getString(1);
      String fileContents = rs.getString(2);
      String label = rs.getString(3);
      
      
      int counter = 1;
      File f = new File(String.format("res/test/%s_%04d.java", label, counter));
      while(f.exists()) {
        counter++;
        f = new File(String.format("res/test/%s_%04d.java", label, counter));
      }
      label = String.format("%s_%04d", label, counter);
      
      fileName = fileName.substring(0, fileName.lastIndexOf('.'));
      
      //System.out.println(fileName);
      fileContents = fileContents.replaceAll("(\\W)"+fileName+"(\\W)", "$1"+label+"$2");
      //System.out.println(fileContents);
      
      PrintStream p = new PrintStream(f);
      p.print(fileContents);
      p.close();
      
      //break;
    }
  }
}
