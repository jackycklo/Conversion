package com.jackylo.coversion;

import java.text.DecimalFormat;

/**
 * Created by jackylo on 9/5/2017.
 */

public class Quantity {

    final double value;
    final Unit unit;

    public static enum Unit {
        tsp(1.0d), tbs(0.3333d), cup(0.0208d), oz(0.1666d),
        pint(0.0104d), quart(0.0052d), gallon(0.0013), pound(0.0125d),
        ml(4.9289d), liter(0.0049d), mg(5687.5d), kg(0.0057d);

        final static Unit baseUnit = tsp;

        final double byBaseUnit;

        // Receives the number of tsps the starting unit equals
        private Unit(double inTsp) {
            this.byBaseUnit = inTsp;
        }

        // Converts any other unit value to the number of tsps
        public double toBaseUnit(double value) {
            return value / byBaseUnit;
        }

        // We convert to another unit by using the teaspoon conversion percent
        // defined in the enum
        public double fromBaseUnit(double value) {
            return value * byBaseUnit;
        }

    }

    public Quantity(double value, Unit unit) {
        super();

        this.value = value;
        this.unit = unit;
    }

    // Converts from tsp to the desired unit type
    public Quantity to(Unit newUnit) {
        Unit oldUnit = this.unit;
        return new Quantity(newUnit.fromBaseUnit(oldUnit.toBaseUnit(value)),
                newUnit);
    }

    // Prints out to screen the unit amount and unit type
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.0000");

        return df.format(value) + " " + unit.name();
    }
}
