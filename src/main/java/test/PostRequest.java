package test;

import org.joda.time.DateTime;

/**
 * Created by archit.mittal on 16/01/15.
 */
public class PostRequest {
    private String name;
    private String email;
    private DateTime birthday;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
