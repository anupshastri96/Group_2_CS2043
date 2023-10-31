public class RecipeDriver {
    public static void main(String[] args){
        Recipe a = new Recipe("Cookies");
        System.out.println(a.getRating());
        a.updateAvgRating(5);
        a.updateAvgRating(1);
        System.out.println(a.getRating());


    }
}
