package pres;

import dao.IDao;
import metire.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Scanner scanner= new Scanner(new File("src/config.txt"));
        String daoClassName = scanner.nextLine();

        //Charger une class dans la ram avec le nom daoClassName
        Class cDao =Class.forName(daoClassName);
        //newInstance il appel le constructeur sans paramettre par def
        IDao dao = (IDao) cDao.newInstance();
        String metierClassName=scanner.nextLine();
        Class cMetier=Class.forName(metierClassName);
        IMetier metier=(IMetier) cMetier.newInstance();
        Method method=cMetier.getMethod("setDao",IDao.class);

        method.invoke(metier,dao);
        System.out.println("Resultat =>"+metier.calcul());
        //System.out.println(dao.getData());
    }
}
