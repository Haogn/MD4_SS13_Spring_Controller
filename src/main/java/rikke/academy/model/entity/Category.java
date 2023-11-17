package rikke.academy.model.entity;

public class Category {
    private Integer categotyId ;
    private String categoryName ;
    private Boolean status ;

    public Category(){

    }
    public Category(Integer categotyId, String categoryName, Boolean status) {
        this.categotyId = categotyId;
        this.categoryName = categoryName;
        this.status = status;
    }

    public Integer getCategotyId() {
        return categotyId;
    }

    public void setCategotyId(Integer categotyId) {
        this.categotyId = categotyId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
