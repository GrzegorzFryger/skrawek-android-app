package pl.edu.pjatk.pamo.skrawek.rest.model.accounts;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private String name;
    private Set<PrivilegeType> privileges = new HashSet<>();

    public Role() {
    }

    public Role(String name, Set<PrivilegeType> privileges) {
        this.name = name;
        this.privileges = privileges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PrivilegeType> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<PrivilegeType> privileges) {
        this.privileges = privileges;
    }
}
