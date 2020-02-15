
package com.example.rickmorty.models.characters;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info implements Serializable
{

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("prev")
    @Expose
    private String prev;
    private final static long serialVersionUID = -6517377654229313584L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Info() {
    }

    /**
     * 
     * @param next
     * @param pages
     * @param prev
     * @param count
     */
    public Info(Integer count, Integer pages, String next, String prev) {
        super();
        this.count = count;
        this.pages = pages;
        this.next = next;
        this.prev = prev;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

}
