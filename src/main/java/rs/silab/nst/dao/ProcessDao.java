package rs.silab.nst.dao;

import rs.silab.nst.model.Process;

import java.util.List;

/**
 * Created by kuzmanom on 4/16/2017.
 */
public interface ProcessDao {
    List<Process> findAll(String pib);
}
