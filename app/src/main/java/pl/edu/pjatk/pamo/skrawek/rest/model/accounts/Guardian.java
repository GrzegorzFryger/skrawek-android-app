package pl.edu.pjatk.pamo.skrawek.rest.model.accounts;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class - used when calling REST API
 */
public class Guardian extends Account {
    private List<Child> children = new ArrayList<>();

    public Guardian() {
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Guardian{" +
                "children=" + children +
                '}';
    }
}
