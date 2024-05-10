package com.ntp.apiwithretrofit.respone;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class MovieList {
    private int page;
    private String total_pages;
    private String total_results;

    private List<Movie> results;

    public MovieList(int page, String total_pages, String total_results, List<Movie> results) {
        this.page = page;
        this.total_pages = total_pages;
        this.total_results = total_results;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
