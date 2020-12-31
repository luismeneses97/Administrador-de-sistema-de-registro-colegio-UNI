import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private String url = "colegio.db";
    private Connection connect;
    
    public Conexion(String url){
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    public void conectar(){
        try {
            Class.forName("org.sqlite.JDBC");//para activar el driver
            connect = DriverManager.getConnection("jdbc:sqlite:"+url);
            
        } catch (SQLException e) {
            System.out.println("Error con la conexin a la base de datos: "+e);
        }
          catch (ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null,ex);
        } 
    
    }
    
    public void cerrarConexion(){
        try {
            connect.close();
        }catch (SQLException e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Error Cerrando", e);
        }
    }
   
}