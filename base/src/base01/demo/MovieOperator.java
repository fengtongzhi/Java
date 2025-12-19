package base01.demo;

public class MovieOperator {

    private Movie[] movies;

    public MovieOperator() {
    }

    public MovieOperator(Movie[] movies) {
        this.movies = movies;
    }

    public void searchByName(String inception) {
        //通过名称查找电影
        for (Movie movie : movies) {
            if (movie.getName().equals(inception)) {
                System.out.println("Found Movie:");
                System.out.println("Name: " + movie.getName());
                System.out.println("ID: " + movie.getId());
                System.out.println("Price: " + movie.getPrice());
                System.out.println("Actor: " + movie.getActor());
                return;
            }
        }

    }

    public void printMovies() {
        for (Movie movie : movies) {
            System.out.println("Name: " + movie.getName());
            System.out.println("ID: " + movie.getId());
            System.out.println("Price: " + movie.getPrice());
            System.out.println("Actor: " + movie.getActor());
            System.out.println("-----------------------");
        }
    }

    public void searchById(String m003) {
        //通过ID查找电影
        for (Movie movie : movies) {
            if (movie.getId().equals(m003)) {
                System.out.println("Found Movie:");
                System.out.println("Name: " + movie.getName());
                System.out.println("ID: " + movie.getId());
                System.out.println("Price: " + movie.getPrice());
                System.out.println("Actor: " + movie.getActor());
                return;
            }
        }
    }
}
