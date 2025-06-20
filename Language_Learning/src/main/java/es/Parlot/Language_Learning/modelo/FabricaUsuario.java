package es.Parlot.Language_Learning.modelo;

import es.Parlot.Language_Learning.modelo.enums.rol;

public class FabricaUsuario {
    public Usuario getUsuario(rol tipoUsuario){

        if(tipoUsuario.equals(rol.ROLE_TEACHER)){
            return new Profesor();
        } else if(tipoUsuario.equals(rol.ROLE_STUDENT)){
            return new Alumno();
        }
        return null;
    }
}
