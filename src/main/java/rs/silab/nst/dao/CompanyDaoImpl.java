package rs.silab.nst.dao;

import org.springframework.stereotype.Repository;
import rs.silab.nst.model.Company;

import javax.persistence.NoResultException;


@Repository("companyDao")
public class CompanyDaoImpl extends AbstractDao<Integer, Company> implements CompanyDao {

    @Override
    public Company findByPIB(Company company) {
        try {
            Company c = (Company) getEntityManager()
                    .createQuery("SELECT c FROM Company c WHERE c.pib LIKE :pib")
                    .setParameter("pib", company.getPib())
                    .getSingleResult();
            return c;
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCompany(Company company) {
        update(company);
    }

    @Override
    public void saveCompany(Company company) {
        update(company);
    }
}
