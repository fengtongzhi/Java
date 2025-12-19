package base01.demo;

public class Test {
    public  static void main(String[] args) {
        Movie[] movies = new Movie[6];

        movies[0] = new Movie("Inception", "M001", 12.99, "Leonardo DiCaprio");
        movies[1] = new Movie("The Dark Knight", "M002", 10.99, "Christian Bale");
        movies[2] = new Movie("Interstellar", "M003", 15.99, "Matthew McConaughey");
        movies[3] = new Movie("The Matrix", "M004", 9.99, "Keanu Reeves");
        movies[4] = new Movie("Fight Club", "M005", 8.99, "Brad Pitt");
        movies[5] = new Movie("Pulp Fiction", "M006", 11.99, "John Travolta");

        MovieOperator mo=new MovieOperator(movies);
        mo.printMovies();
        mo.searchByName( "Inception");
        mo.searchById( "M003");
    }
}
