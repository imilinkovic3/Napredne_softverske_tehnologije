package rs.silab.nst.service;

import rs.silab.nst.model.DocumentType;

import java.util.List;

/**
 * Created by kuzmanom on 3/11/2017.
 */
public interface DocumentService {
    List<DocumentType> findAll(String pib);

    DocumentType findById(int id);

    void save(DocumentType document) throws RuntimeException;
}
