package rs.silab.nst.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the process database table.
 */
@Entity
@NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p")
public class Process implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    private String description;

    private String name;

    @Transient
    private String text;

//    //bi-directional many-to-one association to Activity
//    @OneToMany(mappedBy = "processBean", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Activity> activities;

    //bi-directional many-to-one association to Process
    @ManyToOne
    @JoinColumn(name = "parent")
    private Process process;

//    @OneToMany(mappedBy = "process", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Process> processes;

    @Transient
    private List<Object> nodes;

    public Process() {
        nodes = new ArrayList();
    }

    public Process(int id, Company company, String description, String name, String text, Process process) {
        this.id = id;
        this.company = company;
        this.description = description;
        this.name = name;
        this.text = text;
        this.process = process;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
//
//    public List<Activity> getActivities() {
//        return this.activities;
//    }
//
//    public void setActivities(List<Activity> activities) {
//        this.activities = activities;
//    }

    public String getText() {
        return text;
    }

    public void setText() {
        this.text = name;
    }

//    public List<Process> getProcesses() {
//        return this.processes;
//    }
//
//    public void setProcesses(List<Process> processes) {
//        this.processes = processes;
//    }

    public Process getProcess() {
        return this.process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public List<Object> getNodes() {
        return nodes;
    }

    public void setNodes() {
//        if (processes != null) {
//            for (Process process : processes) {
//                nodes.add(process);
//            }}
//        } else if (activities != null) {
//            for (Activity activity : activities) {
//                nodes.add(activity);
//            }
//        }
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", company=" + company +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
//                ", activities=" + activities +
//                ", processes=" + processes +
                ", nodes=" + nodes +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Process) {
            Process process = (Process) obj;
            if (process.getId() == this.getId()) {
                return true;
            }
        }

        return false;    }
}