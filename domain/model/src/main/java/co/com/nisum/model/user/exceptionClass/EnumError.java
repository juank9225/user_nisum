package co.com.nisum.model.user.exceptionClass;

public enum EnumError {
    ERROR_401(401,"Fallo la Autenticacion."),
    ERROR_404(404,"Registro no encontrado."),
    ERROR_400(400, "correo ya registrado."),
    ERROR_500(500,"Error interno en el servidor.");

    private final Integer codigo;
    private final String mesage;

    EnumError(Integer codigo, String mesage) {
        this.codigo = codigo;
        this.mesage = mesage;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getMesage() {
        return mesage;
    }
}
