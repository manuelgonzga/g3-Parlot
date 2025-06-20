package es.Parlot.Language_Learning.modelo;

import es.Parlot.Language_Learning.modelo.enums.rol;

public class Admin extends Usuario{

    public Admin() {
        super(rol.ROLE_ADMIN);
    }
}