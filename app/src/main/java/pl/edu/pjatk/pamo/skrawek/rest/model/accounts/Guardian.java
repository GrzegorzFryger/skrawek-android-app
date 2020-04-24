package pl.edu.pjatk.pamo.skrawek.rest.model.accounts;

import java.util.Set;

public class Guardian extends Account {
    private Set<Child> children;

    public Guardian() {
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }
}
