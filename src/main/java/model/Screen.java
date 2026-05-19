package model;

public class Screen extends Product implements ScreenSpec{
    String resolution;
    int refreshRate;
    int responseTime;

    public Screen(String resolution, int refreshRate, int responseTime) {
        super("Screen");
        super.setProductType("Screen");
        super.setName("Monitor "+resolution);
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.responseTime = responseTime;

        super.classification = this.toString();
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
