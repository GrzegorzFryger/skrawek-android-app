package pl.edu.pjatk.pamo.skrawek.rest.model.calendar;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Model class - used when calling REST API
 */
public class Absence {
    private Long id;
    private UUID childId;
    private LocalDate date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
