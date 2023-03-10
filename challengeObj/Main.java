import java.util.Scanner;

public class Main {

  static Store store = new Store();

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    Movie[] movies = new Movie[] {
        new Movie("The Shawshank Redemption", "BlueRay", 9.2),
        new Movie("The Godfather", "BlueRay", 9.1),
        new Movie("The Godfather: Part II", "DVD", 9.0),
        new Movie("The Dark Knight", "BlueRay", 9.0),
        new Movie("Schindler's List", "DVD", 8.9),
        new Movie("The Lord of the Rings: The Return of the King", "BlueRay", 8.9),
        new Movie("Pulp Fiction", "DVD", 8.8),
        new Movie("The Lord of the Rings: The Fellowship of the Ring", "DVD", 8.8),
    };

    for (int i = 0; i < movies.length; i++) {
      store.setMovies(i, movies[i]);
    }

    System.out.println("********************************MOVIE STORE*******************************");
    printStore();

    System.out.println("Type continue to go");
    String userInput = scanner.next();

    while (userInput.equals("continue")) {
      System.out.print("\nPlease choose an integer between 0 - 9: ");
      int index = scanner.nextInt();
      Movie movie = store.getMovie(index);
      System.out.print("Set a new rating for " + movie.getName() + ": ");
      double rating = scanner.nextDouble();
      movie.setRating(rating);

      store.setMovies(index, movie);

      System.out.println("To edit another rating, type: 'continue'");

      userInput = scanner.next();
      printStore();
    }

  }

  public static void printStore() {
    System.out.println("********************************MOVIE STORE*******************************");
    System.out.println(store);
  }

}
