package com.marshal.halcyon.core.component;

/**
 * @auth: Marshal
 * @date: 2019/4/6
 * @desc:
 */
public class SessionContext {

    private Long userId = -1L;
    private Long roleId = -1L;
    private Long employeeId = -1L;
    private String employeeCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
