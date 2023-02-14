public class Store {
  Movie[] movies;

  public Store(){
    this.movies = new Movie[10];
  }

  public Movie getMovie(int index){
    return new Movie(this.movies[index]);
  }

  public void setMovies(int index, Movie movie){
    this.movies[index] = new Movie(movie);
  }

  public String toString() {
    String temp = "";
    for(Movie movie : this.movies){
      if(movie == null){
        continue;
      }

      temp += movie.toString() + "\n";
    }
    return temp;
  }
}
