package rs.silab.nst.dao;

import org.springframework.stereotype.Repository;
import rs.silab.nst.model.Process;

import java.util.List;

/**
 * Created by kuzmanom on 4/16/2017.
 */
@Repository("processDao")
public class ProcessDaoImpl extends AbstractDao<Integer, Process> implements ProcessDao {

    @Override
    public List<Process> findAll(String pib) {
        List<Process> processes = getEntityManager()
                .createQuery("SELECT p FROM Process p WHERE company.pib = :pib")
                .setParameter("pib", pib)
                .getResultList();
        return processes;
    }
}
