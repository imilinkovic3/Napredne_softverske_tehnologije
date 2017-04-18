package rs.silab.nst.service;

import rs.silab.nst.model.DocumentDescriptor;

import java.util.List;

/**
 * Created by kuzmanom on 3/21/2017.
 */
public interface DocumentDescriptorService {
    void save(List<DocumentDescriptor> documentDescriptors);
}
