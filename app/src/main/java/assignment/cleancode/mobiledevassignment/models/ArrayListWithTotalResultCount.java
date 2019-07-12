package assignment.cleancode.mobiledevassignment.models;

import java.util.ArrayList;

public class ArrayListWithTotalResultCount<T> extends ArrayList<T> {


    int totalNumberOfResults;

    int totalNumberOfPages;

    int page;


    public int getTotalNumberOfResults() {
        return totalNumberOfResults;
    }

    public void setTotalNumberOfResults(int totalNumberOfResults) {
        this.totalNumberOfResults = totalNumberOfResults;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }

}