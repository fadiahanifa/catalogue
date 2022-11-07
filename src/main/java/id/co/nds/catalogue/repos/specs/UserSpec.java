package id.co.nds.catalogue.repos.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.models.UserModel;

public class UserSpec implements Specification<UserEntity> {
    
    private UserModel userModel;

    public UserSpec(UserModel userModel) {
        super();
        this.userModel = userModel;
    }

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        
        Predicate p = cb.and();

        // id criteria
        if (userModel.getId() != null && userModel.getId() != 0) {
            p.getExpressions().add(cb.equal(root.get("id"), userModel.getId()));
        }

        // name criteria
        if (userModel.getFullname() != null && userModel.getFullname().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("fullname")),
            "%" + userModel.getFullname().toLowerCase() + "%"));
        }

        // role id criteria
        if (userModel.getRoleId() != null && userModel.getRoleId().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("roleId")),
            "%" + userModel.getRoleId().toLowerCase() + "%"));
        }

        // call number criteria
        if (userModel.getCallNumber() != null && userModel.getCallNumber().length() > 0) {
            p.getExpressions().add(cb.like(cb.lower(root.get("callNumber")),
            "%" + userModel.getCallNumber().toLowerCase() + "%"));
        }

        // rec_status criteria
        if (userModel.getRecStatus() != null &&
        (userModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_ACTIVE)
        || userModel.getRecStatus().trim().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE))) {

            p.getExpressions().add(cb.equal(cb.upper(root.get("recStatus")), 
            userModel.getRecStatus().toUpperCase()));
        }

        cq.orderBy(cb.asc(root.get("id")));

        return p;
    }
}