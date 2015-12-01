package com.example.usuario.calculo_fct_plus;

/**
 * Created by Usuario on 01/12/2015.
 */
public class Empresa
{
    private String Nombre;
    private String Apellidos;
    private String Email;
    private String Telefono;
    private String Direccion;
    private String Web;


    public Empresa( String nombre, String apellidos, String email, String telefono, String direccion, String web)
    {
        Nombre=nombre;
        Apellidos=apellidos;
        Email=email;
        Telefono=telefono;
        Direccion=direccion;
        Web=web;
    }
    
    public  Empresa(){}
    
    
    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String web) {
        Web = web;
    }
}
