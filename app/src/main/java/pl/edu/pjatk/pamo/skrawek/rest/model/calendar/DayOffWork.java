package pl.edu.pjatk.pamo.skrawek.rest.model.calendar;

/**
 * Model class - used when calling REST API
 */
public class DayOffWork {
    private Long id;
    private String date;
    private String name;
    private EventType eventType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
