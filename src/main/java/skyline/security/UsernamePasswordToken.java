package skyline.security;

/**
 * Created by zhanrui on 2015/11/8.
 */
public class UsernamePasswordToken {
    private String username;
    private String password;
    private String hostIp;

    public UsernamePasswordToken(String username, String password, String hostIp) {
        this.username = username;
        this.password = password;
        this.hostIp = hostIp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHostIp() {
        return hostIp;
    }
}
