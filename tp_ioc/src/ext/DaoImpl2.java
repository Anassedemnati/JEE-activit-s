package ext;

import dao.IDao;

public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Vertion Capteur");
        double temp = 1000;
        return temp;
    }
}
