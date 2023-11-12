package command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "command", include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        visible=true, defaultImpl = Class.class)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CommandSearch.class, name = "search"),
        @JsonSubTypes.Type(value = CommandSelect.class, name = "select")
})
@Getter
@Setter
public abstract class Command {
    @JsonProperty("command")
    protected String command;
    @JsonProperty("timestamp")
    protected int timestamp;
    @JsonProperty("username")
    protected String username;

    public Command() {}

    public Command(String command, String username, int timestamp) {
        this.command = command;
        this.username = username;
        this.timestamp = timestamp;
    }

    public abstract Response processCommand();
}
