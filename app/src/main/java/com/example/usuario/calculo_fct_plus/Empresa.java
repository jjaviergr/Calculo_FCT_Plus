package com.example.usuario.calculo_fct_plus;

/**
 * Created by Usuario on 01/12/2015.
 */
public class Empresa
{
    private long numero;
    private String NombreE;
    private String NombreR;
    private String Apellidos;
    private String Email;
    private String Telefono;
    private String Direccion;
    private String Web;


    public Empresa( String nombree,String nombrer, String apellidos, String email, String telefono, String direccion, String web)
    {
        setNombreE(nombree);
        setNombreR(nombrer);
        Apellidos=apellidos;
        Email=email;
        Telefono=telefono;
        Direccion=direccion;
        Web=web;
    }
    
    public  Empresa(){}
    
    
    



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

    public String getNombreE() {
        return NombreE;
    }

    public void setNombreE(String nombreE) {
        NombreE = nombreE;
    }

    public String getNombreR() {
        return NombreR;
    }

    public void setNombreR(String nombreR) {
        NombreR = nombreR;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
}
