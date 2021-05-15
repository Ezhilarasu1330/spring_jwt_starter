package com.springboot.jwt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    public final static Boolean mailValidator(final String mailId) {
        return matcher("^(.+)@(.+)$", mailId);
    }

    public final static Boolean passwordValidator(final String password) {
        return matcher("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", password);
    }

    private final static Boolean matcher(final String regex, final String value) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}
