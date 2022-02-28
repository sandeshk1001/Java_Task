package Homework_4.JavaTask02_28fab.Q7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.StandardGenerator;
import utils.Utility;

import java.sql.*;

public class ProductSequenceId implements StandardGenerator {
    private final String DEFAULT_SEQUENCE_NAME="my_Seq";
    private static int id;
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Session session= Utility.getSession();
        Long length = session.createQuery("select count(product_name) from Product",Long.class).getSingleResult();
        return (length+1);
    }
}
