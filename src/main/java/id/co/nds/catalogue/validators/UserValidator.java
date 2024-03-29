package id.co.nds.catalogue.validators;

import id.co.nds.catalogue.exceptions.ClientException;
import id.co.nds.catalogue.globals.GlobalConstant;

public class UserValidator {
    public void nullCheckUserId(Integer id) throws ClientException {
        if (id == null) {
            throw new ClientException("User id is required");
        }
    }

    public void notNullCheckUserId(Integer id) throws ClientException {
        if (id != null) {
            throw new ClientException("User id is autogenerated, do not input id");
        }
    }

    public void nullCheckFullname(String fullname) throws ClientException {
        if (fullname == null) {
            throw new ClientException("User name is required");
        }
    }

    public void nullCheckRoleId(String RoleId) throws ClientException {
        if (RoleId == null) {
            throw new ClientException("User role id is required");
        }
    }

    public void nullCheckObject(Object o) throws ClientException {
        if (o == null) {
            throw new ClientException("User not found");
        }
    }
    
    public void validateUserId(Integer id) throws ClientException {
        if (id <= 0) {
            throw new ClientException("User id input is invalid");
        }
    }

    public void validateFullname(String fullname) throws ClientException {
        if (fullname.trim().equalsIgnoreCase("")) {
            throw new ClientException("User fullname is required");
        }
    }

    public void validateRoleId(String roleId) throws ClientException {
        if (roleId.length() != 5 || !roleId.startsWith("R")) {
            throw new ClientException("User role id contains five digits and starts with R");
        }
    }

    public void validateCallNumber (String callNumber) throws ClientException {
        if ((callNumber.length() < 9 ||  callNumber.length() > 14) || !(callNumber.startsWith("0") || callNumber.startsWith("+62")))  {
            throw new ClientException("Call number contains nine to twelve digits and starts with 0 or +62");
        }
    }

    public void validateRecStatus(String id, String recStatus) throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException("User with id - " + id + " has already been deleted");
        }
    }
    
}
