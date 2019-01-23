package mblog.nblog.entity;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.time.LocalDateTime;

public class IP {
    private  int id;

    private String IPAddress;

    private String loginTime;

    public String getLoginTime() {
        return loginTime;
    }

    private String IPAddressName;

    public String getIPAddressName() {
        return IPAddressName;
    }

    public void setIPAddressName(String IPAddressName) {
        this.IPAddressName = IPAddressName;
    }

    @Override
    public String toString() {
        return "IP{" +
                "id=" + id +
                ", IPAddress='" + IPAddress + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", IPAddressName='" + IPAddressName + '\'' +
                '}';
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IP ip = (IP) o;
        return Objects.equals(IPAddress, ip.IPAddress) &&
                Objects.equals(loginTime, ip.loginTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(IPAddress, loginTime);
    }

}
