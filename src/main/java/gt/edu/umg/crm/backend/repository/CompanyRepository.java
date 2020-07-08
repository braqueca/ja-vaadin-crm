package gt.edu.umg.crm.backend.repository;

import gt.edu.umg.crm.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
