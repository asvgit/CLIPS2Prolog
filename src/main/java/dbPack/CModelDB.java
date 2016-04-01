package dbPack;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import mainPack.CModelVED;
import mainPack.Consts;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ArlSava on 21.10.2015.
 */
public class CModelDB {
    private CModelVED myModel;
    private Connection connection;
    private java.sql.Statement statement;
    private java.sql.PreparedStatement preparedStatement;
    public CModelDB(CModelVED mm){
        myModel=mm;
        initialization();
    }
    public void shutdownBD(){
        try {
            statement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getText(){
        String s = "";
        try {
            /*if(!connection.isClosed())
                System.out.println("Connection loaded!");*/
            ResultSet res = statement.executeQuery(Consts.GET_RESERVED_WORDS);
            while(res.next()){
                s+=res.getString("id")+" ";
                s+=res.getString("name")+": ";
                s+=res.getString("value")+"\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public String[][] getDictionary(){
        String s = "";
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        try {
            /*if(!connection.isClosed())
                System.out.println("Connection loaded!");*/
            ResultSet res = statement.executeQuery(Consts.GET_RESERVED_WORDS);
            while(res.next()){
                names.add(res.getString("name"));
                values.add(res.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] base = new String[names.size()][2];
        for(int i=0;i<names.size();i++){
            base[i][0]=names.get(i);
            base[i][1]=values.get(i);
        }
        return base;
    }
    public void delTable() throws SQLException {
        try {
            preparedStatement = connection.prepareStatement(Consts.CLEAR_TABLE);
            preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(Consts.CLEAR_TABLE+" - ERROR");
        }
        /*ResultSet res = statement.executeQuery("SELECT * FROM rules;");
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        while(res.next()){
            names.add(res.getString("name"));
            values.add(res.getString("value"));
            numbers.add(res.getInt("number"));
        }

        /*preparedStatement = connection.prepareStatement(
                "CREATE TABLE my_rules (\n" +
                        "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  name varchar(45) NOT NULL,\n" +
                        "  value varchar(135) NOT NULL,\n" +
                        "  PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8"
        );
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("insert into my_rules (name,value) values(?,?);");
        for(int i=0;i<names.size();i++){
            for(int j=0;j<numbers.get(i);j++) {
                preparedStatement.setString(1, names.get(i)+j);
                preparedStatement.setString(2, "");
                preparedStatement.executeUpdate();
            }
        }*//*
        for(int i=0;i<names.size();i++) {
            System.out.println(names.get(i) + " " + values.get(i) + " " + numbers.get(i));
            for(int j=0;j<numbers.get(i);j++) {
                preparedStatement = connection.prepareStatement("CREATE TABLE " + values.get(i) + j + " ("
                        + "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  name varchar(45) NOT NULL,\n" +
                        "  value varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (id)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8");
                preparedStatement.executeUpdate();
                res = statement.executeQuery("SELECT value FROM my_rules WHERE name=\'" + names.get(i) + j + "\';");
                String line = "";
                String name = "";
                String value = "";
                while (res.next()) {
                    line += res.getString("value");
                }
                System.out.println(line);
                for (int z = 0; z < line.length(); z++) {
                    if (line.charAt(z) == ' ') {
                        value += line.charAt(z + 1);
                        z += 2;
                        preparedStatement = connection.prepareStatement("insert into " + values.get(i) + j + "(name,value) values(?,?);");
                        preparedStatement.setString(1, name);
                        if (value.equals("0"))
                            preparedStatement.setString(2, "nonterminal");
                        else
                            preparedStatement.setString(2, "terminal");
                        preparedStatement.executeUpdate();
                        value = "";
                        name = "";
                    } else
                        name += line.charAt(z);
                }

            }}*/
    }
    public void setSYNTResult(String[][] scanned) throws SQLException {
        //preparedStatement = connection.prepareStatement(Consts.CLEAR_TABLE);
        //preparedStatement.executeUpdate();
        //preparedStatement.execute();
        preparedStatement = connection.prepareStatement(Consts.CREATE_TABLE);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(Consts.FILL_TABLE);
        for(int i=0;i<scanned.length;i++){
            preparedStatement.setString(1,scanned[i][0]);
            preparedStatement.setString(2,scanned[i][1]);
            preparedStatement.execute();
        }
    }
    public String[][] getRules(){
        String s = "";
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> values = new ArrayList<String>();
        try {
            /*if(!connection.isClosed())
                System.out.println("Connection loaded!");*/
            ResultSet res = statement.executeQuery(Consts.GET_MY_RULES);
            while(res.next()){
                names.add(res.getString("name").substring(0,res.getString("name").length()-1));
                values.add(res.getString("value"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] base = new String[names.size()][2];
        for(int i=0;i<names.size();i++){
            base[i][0]=names.get(i);
            base[i][1]=values.get(i);
        }
        return base;
    }
    private void initialization(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(Consts.URL,Consts.USERNAME,Consts.PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
