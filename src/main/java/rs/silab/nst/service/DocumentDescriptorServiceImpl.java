package rs.silab.nst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.silab.nst.dao.DocumentDescriptorDao;
import rs.silab.nst.model.DocumentDescriptor;

import java.util.List;

/**
 * Created by kuzmanom on 3/21/2017.
 */
@Service("documentDescriptorService")
@Transactional
public class DocumentDescriptorServiceImpl implements DocumentDescriptorService {
    @Autowired
    private DocumentDescriptorDao documentDescriptorDao;

    @Override
    public void save(List<DocumentDescriptor> documentDescriptors) {
        documentDescriptorDao.save(documentDescriptors);
    }
}
