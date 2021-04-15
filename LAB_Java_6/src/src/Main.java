package src;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;

public class Main{
    static Logger LOGGER = LOGGER = Logger.getLogger(Main.class.getName());
    static {
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("D:\\University\\Labs\\JAVA\\LAB_Java_6\\src\\log\\logManager.log");
            LOGGER = Logger.getLogger(Main.class.getName());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int result1;

    public static void main(String[] args) {
        //java -classpath D:\University\Labs\JAVA\LAB_Java_6\mysql\mssql-jdbc-8.2.2.jre11.jar
        try{
            Class.forName("org.mariadb.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection =  DriverManager.getConnection("jdbc:mariadb://localhost:3306/java_jdcb?user=root&password=")){
                System.out.println("Connection succesfull!");

                Statement statement = connection.createStatement();
                //statement.executeUpdate("create table java_jdcb.product\n" +
                //        "(\n" +
                //        "    name varchar(100) charset utf8  not null ,\n" +
                //        "    discription varchar(2000) charset utf8 null,\n" +
                //        "    realesedate date                       null,\n" +
                //        "    parametres  int                        not null,\n" +
                //        "    grouproduct varchar(50) charset utf8  not null\n" +
                //        ");");
                LOGGER.log(Level.INFO, "Select request");

                var result1 = statement.executeQuery("select * from java_jdcb.grouparametres inner join  java_jdcb.product p on grouparametres.name = p.pname  where grouproducts = 'TV'");
                
                while(result1.next()){
                    System.out.println(result1.getString("pname") + " Дата производства: " + result1.getDate("realesedate") + " Мощность:"+ result1.getString("power"));
                }

                result1 = statement.executeQuery("select * from java_jdcb.grouparametres inner join  java_jdcb.product p on grouparametres.name = p.pname  where grouproducts != 'TV'");

                while(result1.next()){
                    System.out.println(result1.getString("pname") + " Дата производства: " + result1.getDate("realesedate") + " Мощность:"+ result1.getString("power"));
                }

                LOGGER.log(Level.INFO, "Insert values using PreparedStatement");

                PreparedStatement preparedStatement = connection.prepareStatement("insert INTO java_jdcb.product (pname, grouproducts) values (?, ?)");
                preparedStatement.setString(1, "Смартфон Samsung Galaxy A32");
                preparedStatement.setString(2, "Mobile");
                //preparedStatement.executeUpdate();

                result1 = statement.executeQuery("select * from java_jdcb.grouparametres inner join  java_jdcb.product p on grouparametres.name = p.pname");

                while(result1.next()){
                    System.out.println(result1.getString("pname") + " Дата производства: " + result1.getDate("realesedate") + " Мощность:"+ result1.getInt("power") + " Напряжение: " + result1.getString("voltage"));
                }
            }



        }
        catch(Exception ex){
            LOGGER.log(Level.INFO, "\"Connection failed...\"");
            LOGGER.log(Level.INFO, ex.getMessage());
        }
    }
}