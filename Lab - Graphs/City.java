import java.util.Objects;

public class City implements IDisplayable {

    final static int width = 1600;
    final static int height = 900;

    private String city;
    private String state;
    private double lat;
    private double lon;

    private static double maxLat = 0.0;
    private static double minLat = 90.0;
    private static double maxLon = 0.0;
    private static double minLon = 180;

    City(String city, String state, double lat, double lon) {
        this.city = city;
        this.state = state;
        this.lat = lat;
        this.lon = lon;

        if (this.lat < minLat) {
            minLat = this.lat;
        }
        if (this.lat > maxLat) {
            maxLat = this.lat;
        }
        if (this.lon < minLon) {
            minLon = this.lon;
        }
        if (this.lon > maxLon) {
            maxLon = this.lon;
        }
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }

    @Override
    public int getX() {
        double lonDiff = maxLon - minLon;
        return (int)(((maxLon - this.lon) / lonDiff) * width);
    }

    @Override
    public int getY() {
        double latDiff = maxLat - minLat;
        return (int)(((maxLat - this.lat) / latDiff) * height);
    }

    @Override
    public String getName() {
        return city + ", " + state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;

        return city.equals(city1.city) && state.equals(city1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state);
    }
}
