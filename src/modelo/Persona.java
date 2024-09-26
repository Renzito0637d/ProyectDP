package modelo;

public abstract class Persona {
    // Atributos
    protected String nombres, apellidos, email, telefono, usuario, clave;
    
    // Constructor
    public Persona() {    }
    public Persona(String nombres, String apellidos, String email, String telefono, String usuario, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
    }    
    
    // Encapsulamiento
    public String getNombres() {        return nombres;    }
    public void setNombres(String nombres) {        this.nombres = nombres;    }
    public String getApellidos() {        return apellidos;    }
    public void setApellidos(String apellidos) {        this.apellidos = apellidos;    }
    public String getEmail() {        return email;    }
    public void setEmail(String email) {        this.email = email;    }
    public String getTelefono() {        return telefono;    }
    public void setTelefono(String telefono) {        this.telefono = telefono;    }
    public String getUsuario() {        return usuario;    }
    public void setUsuario(String usuario) {        this.usuario = usuario;    }
    public String getClave() {        return clave;    }
    public void setClave(String clave) {        this.clave = clave;    }

}
