import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Curso { 
    public long id;
    public String nombre;
    public int capacidad;
    public Profesor profesor;
    public ArrayList<Estudiante> estudiantes;

    public Curso(long id, String nombre, int capacidad, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor = profesor;
    }

    public Curso(String nombre, int capacidad, Profesor profesor) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor = profesor;
    }

    public Curso(long id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiante) {
        this.estudiantes = estudiante;
    }
    public long generalProfesor(){
        return 2;
    }
    
    
    public static ArrayList<Curso> extraerRegistroByProfesor(int idProfesor){
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        try {
            Conexion objetoConexion = new Conexion("colegio.db");
            objetoConexion.conectar();
            Statement stmt = objetoConexion.getConnect().createStatement();
            String query = "SElECT id,nombre,capacidad FROM cursos WHERE profesor = "+idProfesor+";";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                int capacidad = result.getInt("capacidad");
                //int profesor = result.getInt("profesor");
                //Profesor profe = Profesor.Leer().get(profesor-1);

                cursos.add(new Curso(id, nombre, capacidad));
            }
            result.close();
            objetoConexion.cerrarConexion();
        } catch (SQLException e) {
        }
        return cursos;
    }
    
    static void agregarCurso(int idProfesor, int idCurso){
        try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "UPDATE cursos SET profesor = '"+idProfesor+"'  WHERE id = "+idCurso+";";
                
        stmt.executeUpdate(query);
        stmt.close();
        objetoConexion.cerrarConexion();
        } catch (Exception e) {
        }
    }
    public void Crear(){
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();//hace la conexion con la base de datos
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "INSERT INTO cursos (nombre,capacidad)"+
                   " VALUES ('"+this.nombre+"',"+this.capacidad+");";
        String q = "INSERT INTO cursos (nombre, capacidad) VALUES ('ESPAÑOL',34);";
        stmt.execute(query);
        stmt.close();
        objetoConexion.cerrarConexion();
    } catch (SQLException e) {
    }
}
public static void Actualizar(int id, String nombre, int capacidad, int profesor){
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "UPDATE cursos SET nombre='"+nombre+"', celular = "+capacidad+", edad = "+profesor+" "+
                "WHERE id = "+id+";";
        stmt.executeUpdate(query);
        stmt.close();
        objetoConexion.cerrarConexion();
    } catch (Exception e) {
    }
}
public static void Borrar(int id){
        try{
            Conexion objetoConexion = new Conexion("colegio.db");
            objetoConexion.conectar();
            Statement stmt = objetoConexion.getConnect().createStatement();
            String query = "DELETE FROM cursos WHERE id = "+id+";";
            stmt.execute(query);
            stmt.close();
            objetoConexion.cerrarConexion();
        }catch(SQLException ex) {
        
        }
    }

public static ArrayList<Curso> Leer(){
    ArrayList<Curso> cursos = new ArrayList<Curso>();
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "SElECT * FROM cursos;";
        ResultSet result = stmt.executeQuery(query);
        while(result.next()){
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            int capacidad = result.getInt("capacidad");
            int profesor = result.getInt("profesor");
            Profesor profe = Profesor.Leer().get(profesor-1);
                    
            cursos.add(new Curso(id, nombre, capacidad, profe));
        }
        result.close();
        objetoConexion.cerrarConexion();
    } catch (SQLException e) {
    }
    return cursos;
}
    
}