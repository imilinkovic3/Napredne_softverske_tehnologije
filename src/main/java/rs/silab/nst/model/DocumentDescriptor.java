package rs.silab.nst.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "document_descriptor")
public class DocumentDescriptor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Boolean booleanValue;

    @Temporal(TemporalType.DATE)
    private Date dateValue;

    private Double doubleValue;

    private Integer intValue;

    private String stringValue;

    @ManyToOne
    @JoinColumn(name = "documentType")
    private DocumentType documentType;

    private String descriptorType;

    private Boolean required;

    @Transient
    private Object value;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public DocumentDescriptor() {
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getBooleanValue() {
        return this.booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Date getDateValue() {
        return this.dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public double getDoubleValue() {
        return this.doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }


    public void setValue(Object object) {
        if (descriptorType.equals("Number")) {
            setIntValue((Integer) object);
            return;
        }
        if (descriptorType.equals("Decimal")) {
            setDoubleValue((Double) object);
            return;
        }
        if (descriptorType.equals("Text")) {
            setStringValue((String) object);
            return;
        }
        if (descriptorType.equals("Logical")) {
            setBooleanValue((Boolean) object);
            return;
        }
        if (descriptorType.equals("Date")) {
            setDateValue((Date) object);
            return;
        }

    }

    public Object getValue() {
        //impelemntirati kasnije kad vidimo za sta ce nam trebati; mozemo npr da pitamo sta nije null to da vratimo
        return null;
    }


    public String getDescriptorType() {
        return this.descriptorType;
    }

    public void setDescriptorType(String descriptorType) {
        this.descriptorType = descriptorType;
    }
}