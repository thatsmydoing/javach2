package edu.ateneo.other;

import java.io.FileReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import au.com.bytecode.opencsv.CSVReader;

public class Relabel {
  public static void main(String[] args) throws Exception {
    Class.forName("org.sqlite.JDBC");
    Connection conn = DriverManager.getConnection("jdbc:sqlite:/media/ACER/Optional/xampplite/htdocs/bluej3/protected/data/production.db");
    //PreparedStatement ps = conn.prepareStatement("UPDATE NonLiteralError SET label=? WHERE nonLiteralError=? AND compileSessionEntryId IN (SELECT compileSessionEntryId FROM ErrorClass WHERE error=?);");
    PreparedStatement ps = conn.prepareStatement("UPDATE NonLiteralError SET label=? WHERE nonLiteralError=? AND compileSessionEntryId IN (SELECT id FROM CompileSessionEntry WHERE messageText=?);");
    CSVReader reader = new CSVReader(new FileReader("test.csv"));
    PrintStream writer = new PrintStream("labels.sql");
    String[] next = reader.readNext();
    while(next != null) {
      String label = next[4];
      if(label.equals("-")) {
        label = "ACTUAL";
      }
      else if(label.equals("")) {
        label = "OTHER";
      }
      ps.setString(1, next[0]+"_"+label);
      ps.setString(2, next[2]);
      ps.setString(3, next[1]);
      ps.execute();
      //writer.printf("UPDATE NonLiteralError SET label='%s_%s' WHERE nonLiteralError='%s' AND compileSessionEntryId IN (SELECT compileSessionId FROM ErrorClass WHERE error='%s');%n", next[0], label, next[2], next[1]);
      next = reader.readNext();
    }
  }
}
