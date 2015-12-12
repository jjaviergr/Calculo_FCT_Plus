package com.example.usuario.calculo_fct_plus;

/**
 * Created by Usuario on 01/12/2015.
 */
public class Alumno 
{
    private long Numero;
    private String Nombre;
    private String Apellidos;
    private String Telefono;
    private String Correo;
    private String YearInicioFct;
    private String Finic;
    private String Ffin;
    private String HorasPd;
    private String NumDias;
    private String HorasTfct;
    private long  key_empresa;
    
    public Alumno( String nombre, String apellidos, String telefono, String correo, String yeariniciofct, String finic, String ffin, String horaspd, String numdias, String horastfct)
    {

        Nombre=nombre;
        Apellidos=apellidos;
        Telefono=telefono;
        Correo=correo;
        YearInicioFct=yeariniciofct;
        Finic=finic;
        Ffin=ffin;
        HorasPd=horaspd;
        NumDias=numdias;
        HorasTfct=horastfct;

    }

public Alumno()
{}
    
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getYearInicioFct() {
        return YearInicioFct;
    }

    public void setYearInicioFct(String yearInicioFct) {
        YearInicioFct = yearInicioFct;
    }

    public String getFinic() {
        return Finic;
    }

    public void setFinic(String finic) {
        Finic = finic;
    }

    public String getFfin() {
        return Ffin;
    }

    public void setFfin(String ffin) {
        Ffin = ffin;
    }

    public String getHorasPd() {
        return HorasPd;
    }

    public void setHorasPd(String horasPd) {
        HorasPd = horasPd;
    }

    public String getNumDias() {
        return NumDias;
    }

    public void setNumDias(String numDias) {
        NumDias = numDias;
    }

    public String getHorasTfct() {
        return HorasTfct;
    }

    public void setHorasTfct(String horasTfct) {
        HorasTfct = horasTfct;
    }

    public long getNumero() {
        return Numero;
    }

    public void setNumero(long numero) {
        Numero = numero;
    }

    public long getKey_empresa() {
        return key_empresa;
    }

    public void setKey_empresa(long key_empresa) {
        this.key_empresa = key_empresa;
    }
}
