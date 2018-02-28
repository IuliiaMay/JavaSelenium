package soap.test;
import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by User on 26.02.2018.
 */
public class MyIp {
    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("46.0.49.0");
        Assert.assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
