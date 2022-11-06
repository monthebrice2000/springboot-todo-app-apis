package springboottodoappapis.models;

public class Errormessage {
    private String message;

    public Errormessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
