package dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao {

    @Override
    public double getData() {
        /*
        * se connecter a la base de donne pour recupere la temperature
        *
        * */
        System.out.println("avec base de donne");
        double temp =Math.random()*40;
        return temp;
    }
}
