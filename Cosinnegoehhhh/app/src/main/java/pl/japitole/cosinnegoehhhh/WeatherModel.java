public class WeatherModel {

    private String city;
    private String date;
    private double temp;
    private String weatherGroup;
    private String iconId;

    public WeatherModel(){

    }

    public WeatherModel(String city, String date, double temp,
                        String weatherGroup, String iconId){
        this.setCity(city);
        this.setDate(date);
        this.setTemp(temp);
        this.setWeatherGroup(weatherGroup);
        this.setIconId(iconId);
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWeatherGroup() {
        return weatherGroup;
    }

    public void setWeatherGroup(String weatherGroup) {
        this.weatherGroup = weatherGroup;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
}