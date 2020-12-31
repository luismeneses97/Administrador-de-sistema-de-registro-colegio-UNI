import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Estudiante {
    private final static AtomicInteger count = new AtomicInteger(0);
    public long id;
    public String nombre;
    public String celular;
    public int edad;
    ArrayList<Curso> cursos ;

    public Estudiante(long id, String nombre, String celular, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.edad = edad;
    }

    public Estudiante(String nombre, String celular, int edad) {
        this.nombre = nombre;
        this.celular = celular;
        this.edad = edad;
    }

    public Estudiante(long id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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
    
    public static ArrayList<Estudiante> extraerEstudianteByProfesor(int idProfesor){
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        try {
            Conexion objetoConexion = new Conexion("colegio.db");
            objetoConexion.conectar();
            Statement stmt = objetoConexion.getConnect().createStatement();
            String query = "SElECT estudiantes.id, estudiantes.nombre, estudiantes.edad FROM cursos "
                    + "INNER JOIN curso_estudiante ON cursos.id = curso_estudiante.id_curso "
                    + "INNER JOIN estudiantes ON curso_estudiante.id_estudiante = estudiantes.id "
                    + "WHERE cursos.profesor = "+idProfesor+" GROUP BY estudiantes.nombre;";            
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                int edad = result.getInt("edad");

                estudiantes.add(new Estudiante(id, nombre, edad));
            }
            result.close();
            objetoConexion.cerrarConexion();
        } catch (SQLException e) {
        }
        return estudiantes;
    }
    
    public static ArrayList<Estudiante> extraerEstudianteByCurso(int idCurso){
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        try {
            Conexion objetoConexion = new Conexion("colegio.db");
            objetoConexion.conectar();
            Statement stmt = objetoConexion.getConnect().createStatement();
            String query = "SElECT estudiantes.id, estudiantes.nombre, estudiantes.edad FROM cursos "
                    + "INNER JOIN curso_estudiante ON cursos.id = curso_estudiante.id_curso "
                    + "INNER JOIN estudiantes ON curso_estudiante.id_estudiante = estudiantes.id "
                    + "WHERE cursos.id = "+idCurso+" GROUP BY estudiantes.nombre;";            
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                int edad = result.getInt("edad");

                estudiantes.add(new Estudiante(id, nombre, edad));
            }
            result.close();
            objetoConexion.cerrarConexion();
        } catch (SQLException e) {
        }
        return estudiantes;
    }

    static void agregarCurso(int idEstudiante, int idCurso){
        try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "INSERT INTO curso_estudiante (id_curso , id_estudiante) VALUES("+idCurso+","+idEstudiante+");";
                
        stmt.execute(query);
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
        String query = "INSERT INTO estudiantes (nombre, celular, edad)"+
                   " VALUES ('"+this.nombre+"','"+this.celular+"', '"+this.edad+"');";
        stmt.execute(query);
        stmt.close();
        objetoConexion.cerrarConexion();
    } catch (SQLException e) {
    }
}
public static void Actualizar(int id, String nombre, String celular, int edad){
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "UPDATE estudiantes SET nombre='"+nombre+"', celular = '"+celular+"', edad = '"+edad+"' "+
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
            String query = "DELETE FROM estudiantes WHERE id = "+id+";";
            stmt.execute(query);
            stmt.close();
            objetoConexion.cerrarConexion();
        }catch(SQLException ex) {
        
        }
    }

public static ArrayList<Estudiante> Leer(){
    ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
    try {
        Conexion objetoConexion = new Conexion("colegio.db");
        objetoConexion.conectar();
        Statement stmt = objetoConexion.getConnect().createStatement();
        String query = "SElECT * FROM estudiantes;";
        ResultSet result = stmt.executeQuery(query);
        while(result.next()){
            int id = result.getInt("id");
            String nombre = result.getString("nombre");
            String celular = result.getString("celular");
            int edad = result.getInt("edad");
            estudiantes.add(new Estudiante(id, nombre, celular,edad));
        }
        result.close();
        objetoConexion.cerrarConexion();
    } catch (SQLException e) {
    }
    return estudiantes;
}
    
}