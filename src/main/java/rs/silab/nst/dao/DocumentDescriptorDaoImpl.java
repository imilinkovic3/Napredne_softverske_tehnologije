package rs.silab.nst.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import rs.silab.nst.model.DocumentDescriptor;

import java.util.List;

/**
 * Created by kuzmanom on 3/21/2017.
 */
@Repository("documentDescirptorDao")
public class DocumentDescriptorDaoImpl extends AbstractDao<Integer, DocumentDescriptor> implements DocumentDescriptorDao {
    @Override
    public void save(List<DocumentDescriptor> documentDescriptors) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = (Session) entityManager.getDelegate();
            transaction = session.getTransaction();
            transaction.setTimeout(5);
            for (DocumentDescriptor descriptor : documentDescriptors) {
                if (descriptor.getName() != null) {
                    update(descriptor);
                }
            }
            transaction.commit();
        } catch (RuntimeException e) {
            try {
                transaction.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
