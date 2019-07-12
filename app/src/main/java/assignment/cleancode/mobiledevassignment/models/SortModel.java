package assignment.cleancode.mobiledevassignment.models;

public class SortModel {
    private int id;
    private int titleRef;
    private int drawableRef;
    private String sort;
    private String sortingIndexPart;

    public SortModel(int id, int titleRef, int drawableRef, String sort, String sortingIndexPart) {
        this.id = id;
        this.titleRef = titleRef;
        this.drawableRef = drawableRef;
        this.sort = sort;
        this.sortingIndexPart = sortingIndexPart;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitleRef() {
        return titleRef;
    }

    public void setTitleRef(int titleRef) {
        this.titleRef = titleRef;
    }

    public int getDrawableRef() {
        return drawableRef;
    }

    public void setDrawableRef(int drawableRef) {
        this.drawableRef = drawableRef;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortingIndexPart() {
        return sortingIndexPart;
    }

    public void setSortingIndexPart(String sortingIndexPart) {
        this.sortingIndexPart = sortingIndexPart;
    }

}
