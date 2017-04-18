package rs.silab.nst.service;

import rs.silab.nst.model.Company;

/**
 * Created by kuzmanom on 2/27/2017.
 */
public interface CompanyService {
    void updateCompany(Company company);

    Company findByPIB(Company company);
}
