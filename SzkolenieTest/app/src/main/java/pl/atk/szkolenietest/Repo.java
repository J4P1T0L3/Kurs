package pl.atk.szkolenietest;

import java.io.Serializable;

/**
 * Created by Tomasz on 04.11.2017.
 */

//todo 10.1: zrób z tej klasy elemnt dzięki któremu można ją przesyłać w intencieKKK
public class Repo implements Serializable {

    private String name;
    private String user;
    private String avatarUrl;
    private String desc;
    private String cloneUrl;
    private String createdDate;
    private String language;
    private int size;
    private int watchers;

    public Repo(String name, String user, String avatarUrl, String desc, String cloneUrl,
                String createdDate, String language, int size, int watchers) {
        this.setName(name);
        this.setUser(user);
        this.setAvatarUrl(avatarUrl);
        this.setDesc(desc);
        this.setCloneUrl(cloneUrl);
        this.setCreatedDate(createdDate);
        this.setLanguage(language);
        this.setSize(size);
        this.setWatchers(watchers);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        if (desc != null)
            this.desc = desc;
        else
            this.desc = "none";
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        Integer sizeTemp = (Integer) size;
        if (sizeTemp != null)
            this.size = size;
        else
            this.size = 0;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        Integer watcherTemp = (Integer) watchers;
        if (watcherTemp != null)
            this.size = watchers;
        else
            this.size = 0;
    }
}
