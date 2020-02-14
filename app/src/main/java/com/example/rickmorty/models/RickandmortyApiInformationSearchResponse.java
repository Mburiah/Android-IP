
package com.example.rickmorty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RickandmortyApiInformationSearchResponse {

    @SerializedName("characters")
    @Expose
    private String characters;
    @SerializedName("locations")
    @Expose
    private String locations;
    @SerializedName("episodes")
    @Expose
    private String episodes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RickandmortyApiInformationSearchResponse() {
    }

    /**
     * 
     * @param characters
     * @param locations
     * @param episodes
     */
    public RickandmortyApiInformationSearchResponse(String characters, String locations, String episodes) {
        super();
        this.characters = characters;
        this.locations = locations;
        this.episodes = episodes;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

}
