package rs.silab.nst.dao;

import rs.silab.nst.model.DocumentDescriptor;

import java.util.List;

/**
 * Created by kuzmanom on 3/21/2017.
 */
public interface DocumentDescriptorDao {
    void save(List<DocumentDescriptor> documentDescriptors);
}
