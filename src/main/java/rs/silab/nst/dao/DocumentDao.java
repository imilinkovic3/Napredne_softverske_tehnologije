package rs.silab.nst.dao;

import rs.silab.nst.model.DocumentType;

import java.util.List;

/**
 * Created by kuzmanom on 3/11/2017.
 */
public interface DocumentDao {
    List<DocumentType> findAll(String pib);

    DocumentType findById(int id) throws RuntimeException;

    void save(DocumentType document) throws RuntimeException;
}
