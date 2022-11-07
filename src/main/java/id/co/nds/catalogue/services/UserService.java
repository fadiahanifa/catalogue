package id.co.nds.catalogue.services;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.catalogue.entities.UserEntity;
import id.co.nds.catalogue.entities.UserInfoEntity;
import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.exceptions.NotFoundException;
import id.co.nds.catalogue.globals.GlobalConstant;
import id.co.nds.catalogue.models.UserModel;
import id.co.nds.catalogue.repos.UserInfoRepo;
import id.co.nds.catalogue.repos.UserRepo;
import id.co.nds.catalogue.repos.specs.UserSpec;
import id.co.nds.catalogue.validators.UserValidator;
import id.co.nds.catalogue.validators.RoleValidator;

@Service
public class UserService implements Serializable{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserInfoRepo userInfoRepo;

    UserValidator userValidator = new UserValidator();
    RoleValidator roleValidator = new RoleValidator();

    public UserEntity add(UserModel userModel) throws ClientException {
        userValidator.notNullCheckUserId(userModel.getId());
        userValidator.nullCheckFullname(userModel.getFullname());
        userValidator.validateFullname(userModel.getFullname());
        userValidator.nullCheckRoleId(userModel.getRoleId());
        userValidator.validateRoleId(userModel.getRoleId());
        userValidator.validateCallNumber(userModel.getCallNumber());

        Long countName = userRepo.countByName(userModel.getFullname());
        if (countName > 0) {
            throw new ClientException("User name already existed");
        }

        Long countNumber  = userRepo.countByCallNumber(userModel.getCallNumber());
        if (countNumber > 0) {
            throw new ClientException("Call number already existed");
        }
        

        // Process
        UserEntity user = new UserEntity();
        user.setFullname(userModel.getFullname());
        user.setRoleId(userModel.getRoleId());
        user.setCallNumber(userModel.getCallNumber());
        user.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        user.setCreatorId(userModel.getActorId() == null ? 0 : userModel.getActorId());

        return userRepo.save(user);
    }

    public List<UserEntity> getAllUser() {
        List<UserEntity> user = new ArrayList<>();
        userRepo.findAll().forEach(user::add);
        
        return user;
    }

    public List<UserEntity> getAllUserByCriteria(UserModel userModel) {
        List<UserEntity> users = new ArrayList<>();
        UserSpec specs = new UserSpec(userModel);
        userRepo.findAll(specs).forEach(users::add);

        return users;
    }

    public List<UserInfoEntity> findUsersByRoleNameWhereNoActive(String roleName) throws ClientException, NotFoundException {
        roleValidator.nullCheckName(roleName);
        roleValidator.validateName(roleName);
        
        List<UserInfoEntity> users = userInfoRepo.findAllByRole(roleName);
        userValidator.nullCheckObject(users);
        
        return users;
    }

    public List<UserEntity> findUsersByRoleName(String roleName) throws ClientException, NotFoundException {
        roleValidator.nullCheckName(roleName);
        roleValidator.validateName(roleName);
        
        List<UserEntity> users = userRepo.findUsersByRoleName(roleName);
        userValidator.nullCheckObject(users);
        
        return users;
    }

    public UserEntity getUserById (Integer id) throws ClientException, NotFoundException {
        userValidator.nullCheckUserId(id);
        userValidator.validateUserId(id);

        UserEntity user = userRepo.findById(id).orElse(null);
        userValidator.nullCheckObject(user);
        
        return user;
    }

    public UserEntity edit(UserModel userModel) throws ClientException, NotFoundException {
        userValidator.nullCheckUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());

        if (!userRepo.existsById(userModel.getId())) {
            throw new NotFoundException("Cannot find user with id: " + userModel.getId());
        }

        UserEntity user = new UserEntity();
        user = getUserById(userModel.getId());

        if (userModel.getFullname() != null) {
            userValidator.validateFullname(userModel.getFullname());

            user.setFullname(userModel.getFullname());
        }

        if (userModel.getRoleId() != null) {
            userValidator.validateRoleId(userModel.getRoleId());

            user.setRoleId(userModel.getRoleId());
        }

        if (userModel.getCallNumber() != null) {
            userValidator.validateFullname(userModel.getCallNumber());
            Long count = userRepo.countByName(userModel.getCallNumber());
            if (count > 0) {
                throw new ClientException("Call Number already existed");
            }

            user.setCallNumber(userModel.getCallNumber());
        }

        user.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
        user.setUpdaterId(userModel.getActorId() == null ? 0 : userModel.getActorId());
        
        return userRepo.save(user);
    }

    public UserEntity delete(UserModel userModel) throws ClientException, NotFoundException {
        userValidator.nullCheckUserId(userModel.getId());
        userValidator.validateUserId(userModel.getId());

        if (!userRepo.existsById(userModel.getId())) {
            throw new NotFoundException("Cannot find user with id: " + userModel.getId());
        }

        UserEntity user = new UserEntity();
        user = getUserById(userModel.getId());

        if (user.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User id (" + userModel.getId() + ") is already deleted");
        }

        user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        user.setDeletedDate(new Timestamp(System.currentTimeMillis()));
        user.setDeleterId(userModel.getActorId() == null ? 0 : userModel.getActorId());

        return userRepo.save(user);
    }


}
