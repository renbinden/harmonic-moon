package io.github.lucariatias.harmonicmoon.currency;

import java.text.DecimalFormat;

public enum Currency {

    QOING("Quoing", "Quoing", "Quoing", 40),
    MOLLIR("Mollir", "Mollir", "Mollirs", 60),
    BONVAR("Bonvar", "Bonvar", "Bonvars", 70),
    AURE("Aure", "Aure", "Aures", 1000),
    YIRE("Yire", "Yire", "Yires", 500);

    private final String name;
    private final String nameSingular;
    private final String namePlural;
    private final int relativeValue;

    private Currency(String name, String nameSingular, String namePlural, int relativeValue) {
        this.name = name;
        this.nameSingular = nameSingular;
        this.namePlural = namePlural;
        this.relativeValue = relativeValue;
    }

    public String getName() {
        return name;
    }

    public String getNameSingular() {
        return nameSingular;
    }

    public String getNamePlural() {
        return namePlural;
    }

    public int getRelativeValue() {
        return relativeValue;
    }

    public double convertTo(double amount, Currency currency) {
        return (amount / relativeValue) * currency.getRelativeValue();
    }

    public String format(double amount) {
        DecimalFormat format = new DecimalFormat();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format.format(amount) + (Math.abs(amount - 1.0D) < 0.01D ? nameSingular : namePlural);
    }

}
