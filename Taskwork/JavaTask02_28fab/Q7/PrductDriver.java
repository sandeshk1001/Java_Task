package Homework_4.JavaTask02_28fab.Q7;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.Utility;

public class PrductDriver {
    public static void main(String[] args) {
        Session  session = Utility.getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(new Product("P-1",1000.0));
        transaction.commit();
        session.close();
    }
}
