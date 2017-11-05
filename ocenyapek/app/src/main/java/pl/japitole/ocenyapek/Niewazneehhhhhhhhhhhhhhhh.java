package pl.japitole.ocenyapek;

import java.io.Serializable;

/**
 * Created by uczen on 2017-11-04.
 */

public class Niewazneehhhhhhhhhhhhhhhh implements Serializable {

    private String name;
    private String logoUrl;
    private String desc;
    private float rating;

    public Niewazneehhhhhhhhhhhhhhhh(String name, String logoUrl, String desc, float rating ){
        this.setName(name);
        this.setLogoUrl(logoUrl);
        this.setDesc(desc);
        this.setRating(rating);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
