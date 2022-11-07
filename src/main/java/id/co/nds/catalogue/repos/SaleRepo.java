package id.co.nds.catalogue.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.SaleEntity;

@Repository
@Transactional
public interface SaleRepo extends JpaRepository<SaleEntity, String>{
    
}
