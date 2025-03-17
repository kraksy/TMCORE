import java.sql.DriverManager;

import com.sun.jdi.connect.spi.Connection;

import krek.tMCORE.TMCORE;

public class Database {

  // if you move every data loading and saving in here
  // the main file will be cleared a lot , and tthats a good thing
  // if you do it more early than any other implementation , its gonna be very lovely
  // thank you future me : 3
  
   public void connect(){
    Class.forName("org.sqlite.JDBC");
    Connection connection = DriverManager.getConnection(link);
   }

}
