package data;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
    private String nameFrom;
    private String nameTo;
    private String message;

    public Message() {
    }

    public Message(String nameFrom, String nameTo, String message) {
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.message = message;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message from" + nameFrom + (nameTo.isEmpty()?"":" to " + nameTo) +
                ", message: " + message + ';';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return nameFrom.equals(message1.nameFrom) &&
                nameTo.equals(message1.nameTo) &&
                message.equals(message1.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameFrom, nameTo, message);
    }
}
