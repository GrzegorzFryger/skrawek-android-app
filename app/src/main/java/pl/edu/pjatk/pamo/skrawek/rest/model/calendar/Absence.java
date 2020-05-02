package pl.edu.pjatk.pamo.skrawek.rest.model.calendar;

import java.util.UUID;

/**
 * Model class - used when calling REST API
 */
public class Absence {
    private Long id;
    private UUID childId;
    private String date;
    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
