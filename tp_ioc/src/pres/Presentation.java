package pres;

import ext.DaoImpl2;
import metire.MetierImpl;
import dao.DaoImpl;

public class Presentation {
    public static void main(String[] args) {
        //DaoImpl dao= new DaoImpl();
        //injection des dependance par instanciation statique => new
        DaoImpl2 daoImpl2 = new DaoImpl2();
        MetierImpl metier = new MetierImpl();
        metier.setDao(daoImpl2);
        System.out.println("Resulta : "+metier.calcul());
    }
}
