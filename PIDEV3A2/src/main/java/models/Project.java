package models;

import java.util.List;

public class Project {
    private int id;
    private String image;
    private String NAME;
    private String description;
    private String targetAudience;
    private String demandInMarket;
    private String developmentTimeline;
    private double budgetFundingRequirements;
    private String riskAnalysis;
    private String marketStrategy;
    private String exitStrategy;
    private String teamBackground;
    private List<String> tags;
    private String uniqueSellingPoints;
    private String dailyPriceOfAssets;
    private String investorsEquity;
    private int category_id;

    // Constructors, getters, and setters

    public Project() {
        // Default constructor
    }

    public Project(int id, String image, String NAME, String description, String targetAudience,
                   String demandInMarket, String developmentTimeline, double budgetFundingRequirements,
                   String riskAnalysis, String marketStrategy, String exitStrategy, String teamBackground,
                   List<String> tags, String uniqueSellingPoints, String dailyPriceOfAssets,
                   String investorsEquity, int category_id) {
        this.id = id;
        this.image = image;
        this.NAME = NAME;
        this.description = description;
        this.targetAudience = targetAudience;
        this.demandInMarket = demandInMarket;
        this.developmentTimeline = developmentTimeline;
        this.budgetFundingRequirements = budgetFundingRequirements;
        this.riskAnalysis = riskAnalysis;
        this.marketStrategy = marketStrategy;
        this.exitStrategy = exitStrategy;
        this.teamBackground = teamBackground;
        this.tags = tags;
        this.uniqueSellingPoints = uniqueSellingPoints;
        this.dailyPriceOfAssets = dailyPriceOfAssets;
        this.investorsEquity = investorsEquity;
        this.category_id = category_id;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getDemandInMarket() {
        return demandInMarket;
    }

    public void setDemandInMarket(String demandInMarket) {
        this.demandInMarket = demandInMarket;
    }

    public String getDevelopmentTimeline() {
        return developmentTimeline;
    }

    public void setDevelopmentTimeline(String developmentTimeline) {
        this.developmentTimeline = developmentTimeline;
    }

    public double getBudgetFundingRequirements() {
        return budgetFundingRequirements;
    }

    public void setBudgetFundingRequirements(double budgetFundingRequirements) {
        this.budgetFundingRequirements = budgetFundingRequirements;
    }

    public String getRiskAnalysis() {
        return riskAnalysis;
    }

    public void setRiskAnalysis(String riskAnalysis) {
        this.riskAnalysis = riskAnalysis;
    }

    public String getMarketStrategy() {
        return marketStrategy;
    }

    public void setMarketStrategy(String marketStrategy) {
        this.marketStrategy = marketStrategy;
    }

    public String getExitStrategy() {
        return exitStrategy;
    }

    public void setExitStrategy(String exitStrategy) {
        this.exitStrategy = exitStrategy;
    }

    public String getTeamBackground() {
        return teamBackground;
    }

    public void setTeamBackground(String teamBackground) {
        this.teamBackground = teamBackground;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUniqueSellingPoints() {
        return uniqueSellingPoints;
    }

    public void setUniqueSellingPoints(String uniqueSellingPoints) {
        this.uniqueSellingPoints = uniqueSellingPoints;
    }

    public String getDailyPriceOfAssets() {
        return dailyPriceOfAssets;
    }

    public void setDailyPriceOfAssets(String dailyPriceOfAssets) {
        this.dailyPriceOfAssets = dailyPriceOfAssets;
    }

    public String getInvestorsEquity() {
        return investorsEquity;
    }

    public void setInvestorsEquity(String investorsEquity) {
        this.investorsEquity = investorsEquity;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    // toString method
    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", NAME='" + NAME + '\'' +
                ", description='" + description + '\'' +
                ", targetAudience='" + targetAudience + '\'' +
                ", demandInMarket='" + demandInMarket + '\'' +
                ", developmentTimeline='" + developmentTimeline + '\'' +
                ", budgetFundingRequirements=" + budgetFundingRequirements +
                ", riskAnalysis='" + riskAnalysis + '\'' +
                ", marketStrategy='" + marketStrategy + '\'' +
                ", exitStrategy='" + exitStrategy + '\'' +
                ", teamBackground='" + teamBackground + '\'' +
                ", tags=" + tags +
                ", uniqueSellingPoints='" + uniqueSellingPoints + '\'' +
                ", dailyPriceOfAssets='" + dailyPriceOfAssets + '\'' +
                ", investorsEquity='" + investorsEquity + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
