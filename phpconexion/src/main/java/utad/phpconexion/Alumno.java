package utad.phpconexion;

/**
 * Created by oscar.fuente on 23/01/2018.
 */

public class Alumno {
    public String nombre,apellido,dni,telefono,nacionalidad,titulacion,cod;

    public Alumno(){}

    public Alumno(String cod,String dni,String nombre, String apellido,String telefono,String nacionalidad,String titulacion){
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.titulacion = titulacion;
    }
}
