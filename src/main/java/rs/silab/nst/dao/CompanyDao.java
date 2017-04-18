package rs.silab.nst.dao;

import rs.silab.nst.model.Company;

public interface CompanyDao {
    Company findByPIB(Company company);

    void updateCompany(Company company);

    void saveCompany(Company company);
}
