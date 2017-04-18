package rs.silab.nst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.silab.nst.dao.DocumentDao;
import rs.silab.nst.dao.ProcessDao;
import rs.silab.nst.model.Process;

import java.util.List;

/**
 * Created by kuzmanom on 4/16/2017.
 */
@Service("processService")
@Transactional
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessDao processDao;

    @Override
    public List<Process> findAll(String pib) {
        return processDao.findAll(pib);
    }
}
