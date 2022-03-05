package metire;

import dao.IDao;

public class MetierImpl implements IMetier {
    //Couplage faible

    private IDao dao;
    @Override
    public double calcul() {
        System.out.println("avec service");
        double tmp=dao.getData();
        double res = tmp*540/Math.cos(tmp*Math.PI);
        return res;
    }
//Injecter dans la variable dao un objet d'une class qui implement l'interface IDao
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
