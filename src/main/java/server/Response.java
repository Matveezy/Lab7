package server;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {

    @Serial
    private static final long serialVersionUID = -2893392192176351154L;

    private String body;
    private boolean isRegistered;
    private boolean isAuthorized;

    public Response() {
    }

    public Response(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    @Override
    public String toString() {
        return "Response{" +
                "body='" + body + '\'' +
                ", isRegistered=" + isRegistered +
                ", isAuthorized=" + isAuthorized +
                '}';
    }
}
