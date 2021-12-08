import java.io.Serializable;

/**
 * O tipo Data.
 */
public class Data implements Serializable {
    private int dia, mes, ano;

    /**
     * Contructor of Date.
     *
     * @param dia the day
     * @param mes the month
     * @param ano the year
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Gets dia.
     *
     * @return o dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Gets mes.
     *
     * @return o mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * Gets ano.
     *
     * @return o ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * Date validator.
     * Checks for invalid day, month and year
     *
     * @return the boolean
     */
    public boolean isDateValid() {
        //Checks for negative numbers
        if (dia <= 0 || mes <= 0 || ano <= 0)
            return false;
            //Checks for "overflow" in the days and months
        else if (dia > 31 || mes > 12)
            return false;
            //Checks for leap years (%4==0 && %100!=0) and makes feb only have 29 days
        else if ((ano % 4 == 0 && ano % 100 != 0) && mes == 2 && dia > 29)
            return false;
            //Checks for months that have 31 days
        else if (mes <= 7 && mes % 2 == 0 && dia > 30)
            return false;
        else return mes <= 7 || mes % 2 != 1 || dia < 31;
    }

    /**
     * Checks if d1 is after d2
     *
     * @param d1 o date1
     * @param d2 o date2
     * @return um boolean
     */
    public boolean isBigger(Data d1, Data d2) {
        boolean val = false;
        //Checks if the year is lesser
        if (d1.getAno() > d2.getAno())
            val = true;
        else if (d1.getAno() == d2.getAno()) {
            if (d1.getMes() > d2.getMes())
                val = true;
            else if (d1.getMes() == d2.getMes()) {
                if (d1.getDia() >= d2.getDia())
                    val = true;
            }
        }
        return val;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }
}
