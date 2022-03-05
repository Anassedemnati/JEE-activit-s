package pres;

import javafx.application.Application;
import metire.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXml {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        IMetier metier =(IMetier) context.getBean("metier");
        System.out.println("Resultat => "+metier.calcul());
    }
}
