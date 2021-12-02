import java.io.Serializable;

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

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    /**
     * Date validator.
     * Checks for invalid day, month and year inputs
     *
     * @param d the date
     * @return the boolean
     */
    public boolean isDateValid(Data d) {
        //Checks for negative numbers
        if (d.getDia() <= 0 || d.getMes() <= 0 || d.getAno() <= 0)
            return false;
            //Checks for "overflow" in the days and months
        else if (d.getDia() > 31 || d.getMes() > 12)
            return false;
            //Checks for leap years (%4==0 && %100!=0) and makes feb only have 29 days
        else if ((d.getAno() % 4 == 0 && d.getAno() % 100 != 0) && d.getMes() == 2 && d.getDia() > 29)
            return false;
            //Checks for months that have 31 days
        else if (d.getMes() <= 7 && d.getMes() % 2 == 0 && d.getDia() > 30)
            return false;
        else return d.getMes() <= 7 || d.getMes() % 2 != 1 || d.getDia() < 31;
    }

    /**
     * Checks if d1 is after d2
     *
     * @param d1 the date1
     * @param d2 the date2
     * @return the boolean
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
        return dia + " " + mes + " " + ano;
    }
}
