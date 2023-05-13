package io.jhoffmann.formulari.util;

import java.util.regex.Pattern;


public final class EmailValidator {
    private static final Pattern regexPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public static boolean validate(String email) {
        return regexPattern.matcher(email).matches();
    }
}
