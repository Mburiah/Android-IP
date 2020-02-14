
package com.example.rickmorty;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episodes implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episode")
    @Expose
    private String episode;
    @SerializedName("characters")
    @Expose
    private List<String> characters = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created")
    @Expose
    private String created;
    private final static long serialVersionUID = 1849476318743331876L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Episodes() {
    }

    /**
     * 
     * @param characters
     * @param created
     * @param name
     * @param airDate
     * @param episode
     * @param id
     * @param url
     */
    public Episodes(Integer id, String name, String airDate, String episode, List<String> characters, String url, String created) {
        super();
        this.id = id;
        this.name = name;
        this.airDate = airDate;
        this.episode = episode;
        this.characters = characters;
        this.url = url;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
