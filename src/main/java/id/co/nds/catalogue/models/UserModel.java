package id.co.nds.catalogue.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserModel extends RecordModel {
    // main
    private Integer id;

    @NotEmpty(message = "User fullname is required")
    private String fullname;

    @NotEmpty(message = "User role Id is required")
    private String roleId;

    @Pattern(regexp = "^(\\+62|0)\\d{9,14}",
    message = "Call number contains nine to twelve digits and starts with 0 or +62")
    private String callNumber;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getCallNumber() {
        return callNumber;
    }
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}
