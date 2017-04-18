package rs.silab.nst.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the operation_type database table.
 * 
 */
@Entity
@Table(name="operation_type")
@NamedQuery(name="OperationType.findAll", query="SELECT o FROM OperationType o")
public class OperationType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="i_o")
	private String iO;

	private String name;

	//bi-directional many-to-one association to Operation
	@OneToMany(mappedBy="operationTypeBean")
	private List<Operation> operations;

	public OperationType() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIO() {
		return this.iO;
	}

	public void setIO(String iO) {
		this.iO = iO;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Operation> getOperations() {
		return this.operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Operation addOperation(Operation operation) {
		getOperations().add(operation);
		operation.setOperationTypeBean(this);

		return operation;
	}

	public Operation removeOperation(Operation operation) {
		getOperations().remove(operation);
		operation.setOperationTypeBean(null);

		return operation;
	}

}