package id.co.nds.catalogue.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CategoryIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        Connection connection = ssci.connection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS seq FROM ms_category");

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int seq = rs.getInt("seq") + 1;
                String code = String.format("PC%04d", seq);
                System.out.println("Generated Stock Code: " + code);
                return code;
            } else {
                throw new HibernateException("Generator failed to generate id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
