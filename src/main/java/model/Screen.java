package model;

public class Screen implements ScreenSpec{
    String resolution;
    int refreshRate;
    int responseTime;

    public Screen(String resolution, int refreshRate, int responseTime) {
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;
    }

    @Override
    public String getResolution() {
        return null;
    }

    @Override
    public int getRefreshRate() {
        return 0;
    }

    @Override
    public int getResponseTime() {
        return 0;
    }

    @Override
    public String toString() {
        return "Resolution : "+resolution+"\nRefresh Rate :"+refreshRate+"\nResponse Time : "+responseTime;
    }
}
