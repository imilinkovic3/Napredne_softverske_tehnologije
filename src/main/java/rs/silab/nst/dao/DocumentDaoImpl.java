package rs.silab.nst.dao;

import org.springframework.stereotype.Repository;
import rs.silab.nst.model.DocumentDescriptor;
import rs.silab.nst.model.DocumentType;

import java.util.List;

/**
 * Created by kuzmanom on 3/11/2017.
 */
@Repository("documentDao")
public class DocumentDaoImpl extends AbstractDao<Integer, DocumentType> implements DocumentDao {
    @Override
    public List<DocumentType> findAll(String pib) {
        List<DocumentType> documents = getEntityManager()
                .createQuery("SELECT d FROM DocumentType d WHERE company.pib = :pib")
                .setParameter("pib", pib)
                .getResultList();
        return documents;
    }

    @Override
    public DocumentType findById(int id) throws RuntimeException {
        DocumentType document = (DocumentType) getEntityManager()
                .createQuery("SELECT d FROM DocumentType d WHERE d.id LIKE :id")
                .setParameter("id", id)
                .getSingleResult();
        return document;
    }

    @Override
    public void save(DocumentType document) throws RuntimeException {
        for (DocumentDescriptor descriptor : document.getDocumentDescriptors()) {
            descriptor.setDocumentType(document);
        }
        update(document);
    }
}
