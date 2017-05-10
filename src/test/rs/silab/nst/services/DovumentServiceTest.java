package rs.silab.nst.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import rs.silab.nst.config.AppConfig;
import rs.silab.nst.model.Company;
import rs.silab.nst.model.DocumentType;
import rs.silab.nst.model.Status;
import rs.silab.nst.model.User;
import rs.silab.nst.service.DocumentService;
import rs.silab.nst.service.DocumentServiceImpl;
import rs.silab.nst.service.UserService;
import rs.silab.nst.service.UserServiceImpl;

import javax.swing.text.Document;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by milinkoi on 09.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class DovumentServiceTest {

//    @Override
//    public List<DocumentType> findAll(String pib) {
//        return documentDao.findAll(pib);
//    }
//
//    @Override
//    public DocumentType findById(int id) {
//        return documentDao.findById(id);
//    }
//
//    @Override
//    public void save(DocumentType document) throws RuntimeException {
//        documentDao.save(document);
//    }

    @Autowired
    private DocumentService documentService;

    private DocumentType document;



    @Before
    public void setUp() {
        document = new DocumentType();
        document.setName("Racun");
        document.setId(23);
        document.setStatus(Status.NOT_INSTANTIATED);
        Mockito.when(documentService.findById(1)).thenReturn(document);
    }

    @After
    public void tearDown() {
        Mockito.verify(this.documentService, VerificationModeFactory.times(1))
                .findById(23);
        Mockito.reset();
    }

    @Test
    public void findByIdAndSaveDocumentTest() throws Exception {
        documentService.save(document);
        assertEquals(document,documentService.findById(23));
    }
    @Test
    public void findAllDocumentsTest() throws Exception {
        Company company = new Company();
        company.setPib("12345");
        int size = documentService.findAll(company.getPib()).size();
        document.setCompany(company);
        document.setId(24);
        documentService.save(document);
        assertEquals((size+1),documentService.findAll(company.getPib()).size());
    }

    @Configuration
    static class DocumentServiceTestContextConfiguration {
        @Bean
        public DocumentService documentService() {
            return new DocumentServiceImpl();
        }
    }


}
