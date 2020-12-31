
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Profesor profee = Profesor.Leer().get(0);
//        Curso curso = new Curso("Internet de las cosas", 23, profee);
//        curso.Crear();
        Scanner sc = new Scanner(System.in);
        // 3 profesores
        for (int i = 0; i < 3; i++) {
            long id = Long.parseLong(sc.nextLine());
            String nombre = sc.nextLine();
            String correo = sc.nextLine();
            int edad = Integer.parseInt(sc.nextLine());
            
            Profesor p = new Profesor(id, nombre, correo, edad);
            p.Crear();
        }
        
        // 5 Estudiantes
        for (int i = 0; i < 5; i++) {
            long id = Long.parseLong(sc.nextLine());
            String nombre = sc.nextLine();
            String celular = sc.nextLine();
            int edad = Integer.parseInt(sc.nextLine());
            
            Estudiante e = new Estudiante(id, nombre, celular, edad);
            e.Crear();
        }
        // 4 cursos
        for (int i = 0; i < 4; i++) {
            long id = Long.parseLong(sc.nextLine());
            String nombre = sc.nextLine();
            int capacidad = Integer.parseInt(sc.nextLine());
            
            Curso c = new Curso(id, nombre, capacidad);
            c.Crear();
            
        }

        

        //16 operaciones en total
        //1 -> Asignando profesor a curso
        //2 -> Asignando estudiante a curso
        for (int i = 0; i < 16; i++) {
            int operacion = Integer.parseInt(sc.nextLine());
            String op = sc.nextLine();
            String[] ids = op.split(" - ");
            if (operacion == 1) { //update
                int idProfesor = Integer.parseInt(ids[0]);
                int idCurso = Integer.parseInt(ids[1]);
                Curso.agregarCurso(idProfesor,idCurso);
            } 
            else {
                int idEstudiante = Integer.parseInt(ids[0]);
                int idCurso = Integer.parseInt(ids[1]);

                Estudiante.agregarCurso(idEstudiante, idCurso);
            }
        }

//        Rector
        long idProfesor = Long.parseLong(sc.nextLine());
        ArrayList<Curso> cursos = Curso.extraerRegistroByProfesor((int) idProfesor);

        for (Curso curso : cursos) {
            System.out.println("Curso: " + curso.getId() + " - " + curso.getNombre() + " - " + curso.getCapacidad());
        }

        idProfesor = Long.parseLong(sc.nextLine());
        ArrayList<Estudiante> estudiantes = Estudiante.extraerEstudianteByProfesor((int)idProfesor);
        
        for (Estudiante estudiante : estudiantes) {
            System.out.println("Estudiante: "+estudiante.getId()+" - "+estudiante.getNombre()+" - "+estudiante.getEdad());
        }
        
        long idCurso = Long.parseLong(sc.nextLine());
        estudiantes = Estudiante.extraerEstudianteByCurso((int)idCurso);
        
        for (Estudiante estudiante : estudiantes) {
            System.out.println("Estudiante: "+estudiante.getId()+" - "+estudiante.getNombre()+" - "+estudiante.getEdad());
        }
    
    
    
    }

}
