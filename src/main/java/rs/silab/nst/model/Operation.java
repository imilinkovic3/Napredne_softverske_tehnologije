package rs.silab.nst.model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the operation database table.
 */
@Entity
@NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o")
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "document_type")
    private DocumentType documentType;

    //bi-directional many-to-one association to Activity
    @ManyToOne
    @JoinColumn(name = "activity")
    private Activity activityBean;

    //bi-directional many-to-one association to OperationType
    @ManyToOne
    @JoinColumn(name = "operation_type")
    private OperationType operationTypeBean;

    public Operation() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Activity getActivityBean() {
        return this.activityBean;
    }

    public void setActivityBean(Activity activityBean) {
        this.activityBean = activityBean;
    }

    public OperationType getOperationTypeBean() {
        return this.operationTypeBean;
    }

    public void setOperationTypeBean(OperationType operationTypeBean) {
        this.operationTypeBean = operationTypeBean;
    }

}