package com.jbirdvegas.mgdorks;

public class Dork {
    public String ghdbId;
    public String category;
    public String queryString;
    public String shortDescription;
    public String textualDescription;

    public Dork(String ghdbId, String category, String queryString, String shortDescription, String textualDescription) {
        this.ghdbId = ghdbId;
        this.category = category;
        this.queryString = queryString;
        this.shortDescription = shortDescription;
        this.textualDescription = textualDescription;
    }

    public Dork() {
        // do nothing
    }

    public String getGhdbId() {
        return ghdbId;
    }

    public void setGhdbId(String ghdbId) {
        this.ghdbId = ghdbId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTextualDescription() {
        return textualDescription;
    }

    public void setTextualDescription(String textualDescription) {
        this.textualDescription = textualDescription;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dork{");
        sb.append("ghdbId='").append(ghdbId).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", queryString='").append(queryString).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", textualDescription='").append(textualDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}