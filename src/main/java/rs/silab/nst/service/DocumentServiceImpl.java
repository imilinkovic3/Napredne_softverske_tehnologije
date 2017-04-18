package rs.silab.nst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.silab.nst.dao.DocumentDao;
import rs.silab.nst.model.DocumentType;

import java.util.List;

/**
 * Created by kuzmanom on 3/11/2017.
 */
@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;


    @Override
    public List<DocumentType> findAll(String pib) {
        return documentDao.findAll(pib);
    }

    @Override
    public DocumentType findById(int id) {
        return documentDao.findById(id);
    }

    @Override
    public void save(DocumentType document) throws RuntimeException {
        documentDao.save(document);
    }
}
