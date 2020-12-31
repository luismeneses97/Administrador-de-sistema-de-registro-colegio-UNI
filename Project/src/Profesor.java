import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Profesor {
    private final static AtomicInteger count = new AtomicInteger(0);
    public long id;
    public String nombre;
    public String correo;
    public int edad;
    ArrayList<Curso> cursos ;

    public Profesor(long id, String nombre, String correo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public Profesor(String nombre, String correo, int edad) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
    public void Crear(){
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();//hace la conexion con la base de datos
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "INSERT INTO profesores (nombre, correo, edad)"+
                   " VALUES ('"+this.nombre+"','"+this.correo+"', '"+this.edad+"');";
        stmt.execute(query);
        stmt.close();
        objetoConexion.cerrarConexion();
    } catch (SQLException e) {
    }
}
public static void Actualizar(long id, String nombre, String correo, int edad){
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "UPDATE profesores SET nombre='"+nombre+"', celular = '"+correo+"', edad = '"+edad+"' "+
                "WHERE id = "+id+";";
        stmt.executeUpdate(query);
        stmt.close();
        objetoConexion.cerrarConexion();
    } catch (Exception e) {
    }
}
public static void Borrar(long id){
        try{
            Conexion objetoConexion = new Conexion("colegio.db");
            objetoConexion.conectar();
            Statement stmt = objetoConexion.getConnect().createStatement();
            String query = "DELETE FROM profesores WHERE id = "+id+";";
            stmt.execute(query);
            stmt.close();
            objetoConexion.cerrarConexion();
        }catch(SQLException ex) {
        
        }
    }

public static ArrayList<Profesor> Leer(){
    ArrayList<Profesor> profesores = new ArrayList<Profesor>();
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "SElECT * FROM profesores;";
        ResultSet result = stmt.executeQuery(query);
        while(result.next()){
            long id = result.getInt("id");
            String nombre = result.getString("nombre");
            String correo = result.getString("correo");
            int edad = result.getInt("edad");
            profesores.add(new Profesor(id, nombre, correo,edad));
        }
        result.close();
        objetoConexion.cerrarConexion();
    } catch (SQLException e) {
    }
    return profesores;
}
    
}