package rs.silab.nst.model;

import java.io.Serializable;
import javax.persistence.*;
import java.lang.*;
import java.util.List;


/**
 * The persistent class for the activity database table.
 */
@Entity
@NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a")
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String description;

    private String name;

    //bi-directional many-to-one association to Process
    @ManyToOne
    @JoinColumn(name = "process")
    private Process processBean;

    //bi-directional many-to-one association to Operation
    @OneToMany(mappedBy = "activityBean")
    private List<Operation> operations;

    public Activity() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Process getProcessBean() {
        return this.processBean;
    }

    public void setProcessBean(Process processBean) {
        this.processBean = processBean;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Operation addOperation(Operation operation) {
        getOperations().add(operation);
        operation.setActivityBean(this);

        return operation;
    }

    public Operation removeOperation(Operation operation) {
        getOperations().remove(operation);
        operation.setActivityBean(null);

        return operation;
    }

}