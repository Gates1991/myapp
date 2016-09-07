package com.hfkj.redchildsupermarket.bean;

import java.util.List;


public class SearchRecommandResponse extends BaseResponse {
    /**
     * response : searchrecommend
     * searchKeywords : ["玩具","男士","孕妇"]
     */

//    private String response;
    private List<String> searchKeywords;

//    public String getResponse() {
//        return response;
//    }
//
//    public void setResponse(String response) {
//        this.response = response;
//    }

    public List<String> getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(List<String> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    @Override
    public String toString() {
        return "SearchRecommandResponse{" +
                "response='" + response + '\'' +
                ", searchKeywords=" + searchKeywords +
                '}';
    }
}
