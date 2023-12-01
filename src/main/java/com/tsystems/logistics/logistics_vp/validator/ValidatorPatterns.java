package com.tsystems.logistics.logistics_vp.validator;

public class ValidatorPatterns {
    protected static final String USERNAME_PATTERN = "[A-Za-z1-9_\\s]+";
    protected static final String CAR_NUMBER_PATTERN = "[A-Z]{3}\\d{4}";
    protected static final String DOUBLE_PATTERN = "[+]?[0-9]*\\.?[0-9]+";
    protected static final String INTEGER_PATTERN = "\\d+";
    protected static final String CITY_AND_ADDRESS_PATTERN = "[A-Za-z\\s]+";
    protected static final String NAME_SURNAME_PATTERN = "[A-Za-z\\d\\s]+";
    protected static final String STATE_PATTERN = "[A-Za-z\\s]+";
    protected static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    protected static final String PHONE_PATTERN =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    protected static final String LOCAL_DATE_TIME_PATTERN =
            "^\\d{4}-(?:0[0-9]{1}|1[0-2]{1})-(0?[1-9]|[12][0-9]|3[01])[tT ]\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?([zZ]|[+-]\\d{2}:\\d{2})";
}
