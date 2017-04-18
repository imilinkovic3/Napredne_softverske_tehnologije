package rs.silab.nst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.silab.nst.dao.CompanyDao;
import rs.silab.nst.model.Company;

/**
 * Created by kuzmanom on 2/27/2017.
 */

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    public Company findByPIB(Company company) {
        return null;
    }
}
