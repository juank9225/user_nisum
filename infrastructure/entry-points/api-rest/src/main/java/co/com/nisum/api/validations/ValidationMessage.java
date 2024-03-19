package co.com.nisum.api.validations;

public enum ValidationMessage {
    EMAILERROR ("El Campo Email es Incorrecto, El formato debe ser (aaaaaaa@dominio.cl)"),
     PASSWORERROR("El Campo password es Incorrecto,Formato (al menos un digito, longitud mayor o igual a 8 Y al menos una letra mayuscula)");

    private final String mesage;

    ValidationMessage(String mesage) {
        this.mesage = mesage;
    }
    public String getMesage() {
        return mesage;
    }
}
