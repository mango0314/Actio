package it.actio.services;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Utility {
    
    public static String mapGiorno(int giorno) {
        switch (giorno) {
            case 0: return "Lunedì";
            case 1: return "Martedì";
            case 2: return "Mercoledì";
            case 3: return "Giovedì";
            case 4: return "Venerdì";
            case 5: return "Sabato";
            case 6: return "Domenica";
            default: return "Sconosciuto";
        }
    }
    
    public static String formatTime(Time t) {
        return t != null ? t.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) : null;
    }
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );
    
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[A-Z])(?=.*[@$!%*?&#().,;:+\\-_=]).{8,}$"
    );

    public static boolean ValidaCredenziali(String email, String password){
        if (!EMAIL_PATTERN.matcher(email).matches() ||
            !PASSWORD_PATTERN.matcher(password).matches()) {
            return false;
        }
        return true;
    }
}
