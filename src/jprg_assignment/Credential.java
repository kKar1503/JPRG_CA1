
package jprg_assignment;

public class Credential {
    private String username;
    private String password;
    private String name;
    private boolean admin;
    private boolean access;
    
    public Credential(String username, String password, String name, boolean admin, boolean access) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.admin = admin;
        this.access = access;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
