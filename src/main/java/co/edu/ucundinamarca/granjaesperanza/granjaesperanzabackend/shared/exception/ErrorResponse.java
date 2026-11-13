package co.edu.ucundinamarca.granjaesperanza.granjaesperanzabackend.shared.exception;

public record ErrorResponse(
        int status,
        String error,
        String message
) {}