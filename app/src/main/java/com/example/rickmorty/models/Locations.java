
package com.example.rickmorty;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locations implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("residents")
    @Expose
    private List<String> residents = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created")
    @Expose
    private String created;
    private final static long serialVersionUID = -6374532012473359869L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Locations() {
    }

    /**
     * 
     * @param created
     * @param name
     * @param residents
     * @param id
     * @param type
     * @param dimension
     * @param url
     */
    public Locations(Integer id, String name, String type, String dimension, List<String> residents, String url, String created) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.dimension = dimension;
        this.residents = residents;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
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
