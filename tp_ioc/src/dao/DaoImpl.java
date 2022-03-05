package dao;

public class DaoImpl implements IDao {

    @Override
    public double getData() {
        /*
        * se connecter a la base de donne pour recupere la temperature
        *
        * */
        double temp =Math.random()*40;
        return temp;
    }
}
