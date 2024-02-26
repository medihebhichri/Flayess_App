package models;

public class Category {
    private int id;
    private String categoryName;
    private String subfield;
    private String typeOfFunding;
    private String profitabilityIndex;

    // Existing constructor
    public Category() {
        // Initialization logic, if any
    }

    // Constructor with parameters
    public Category(int id, String categoryName, String subfield, String typeOfFunding, String profitabilityIndex) {
        this.id = id;
        this.categoryName = categoryName;
        this.subfield = subfield;
        this.typeOfFunding = typeOfFunding;
        this.profitabilityIndex = profitabilityIndex;
    }

    // Getters and Setters
    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Category Name
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Subfield
    public String getSubfield() {
        return subfield;
    }

    public void setSubfield(String subfield) {
        this.subfield = subfield;
    }

    // Type of Funding
    public String getTypeOfFunding() {
        return typeOfFunding;
    }

    public void setTypeOfFunding(String typeOfFunding) {
        this.typeOfFunding = typeOfFunding;
    }

    // Profitability Index
    public String getProfitabilityIndex() {
        return profitabilityIndex;
    }

    public void setProfitabilityIndex(String profitabilityIndex) {
        this.profitabilityIndex = profitabilityIndex;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", subfield='" + subfield + '\'' +
                ", typeOfFunding='" + typeOfFunding + '\'' +
                ", profitabilityIndex='" + profitabilityIndex + '\'' +
                '}';
    }
}
