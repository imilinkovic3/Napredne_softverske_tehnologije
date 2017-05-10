package rs.silab.nst.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "document_type")
@NamedQuery(name = "DocumentType.findAll", query = "SELECT d FROM DocumentType d")
public class DocumentType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Status status;

    //bi-directional many-to-one association to DocumentDescriptor
    @OneToMany(mappedBy = "documentType", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentDescriptor> documentDescriptors;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    public DocumentType() {
        documentDescriptors = new ArrayList<>();
        status = Status.NOT_INSTANTIATED;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<DocumentDescriptor> getDocumentDescriptors() {
        return this.documentDescriptors;
    }

    public void setDocumentDescriptors(List<DocumentDescriptor> documentDescriptors) {
        this.documentDescriptors = documentDescriptors;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", documentDescriptors=" + documentDescriptors +
                ", company=" + company +
                '}';
    }
}
