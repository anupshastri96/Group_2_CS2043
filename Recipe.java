public class Recipe{
    private String name;
    private double rating;
    private int ratingCounter;
    private int ratingSum;

    public Recipe(String name){
        this.name = name;
        ratingCounter = 0;
        ratingSum = 0;
    }
    public void updateAvgRating(int ratingIn){
        if(ratingIn >= 0 && ratingIn <= 5){
            ratingCounter++;
            ratingSum += ratingIn;
            rating = ratingSum/ratingCounter;
        }
        else{
            System.out.println("Please enter a valid rating [0-1]");
        }
    }
    public String getRating(){
        if(ratingSum == 0 && ratingCounter == 0)
            return null;
        return rating + "";
    }

    
}