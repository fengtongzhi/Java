package advanced.movie_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieService {

    private  static List<Movie> movies=new ArrayList<>();

    Scanner in=new Scanner(System.in);

    public void start() {

        while (true) {
            System.out.println("=======电影信息操作系统=======");
            System.out.println("1.上架电影");
            System.out.println("2.下架电影");
            System.out.println("3.查找电影");
            System.out.println("4.封杀明星");
            System.out.println("5.修改电影信息");
            System.out.println("6.查看所有电影");
            System.out.println("0.退出系统");
            System.out.print("请输入操作指令:");
            int command = in.nextInt();
            switch (command) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    queryMovie();
                    break;
                case 4:
                    deleteActor();
                    break;
                case 5:
                    setMovie();
                    break;
                case 6:
                    showAllMovies();
                    break;
                case 0:
                    System.out.println("已退出系统，欢迎下次使用！");
                    return;
                default:
                    System.out.println("指令输入错误，请重新输入！");
            }
        }
    }

    private void showAllMovies() {
        if(movies.isEmpty()){
            System.out.println("当前暂无电影信息！");
        }else {
            System.out.println("当前所有电影信息如下:");
            for (Movie movie : movies) {
                System.out.println(movie);
            }
        }
    }

    private void setMovie() {
        System.out.print("请输入要修改的电影名称:");
        String name = in.next();
        Movie movie=findMovieByName(name);
        if(movie!=null){
            System.out.print("请输入新的电影评分:");
            String score = in.next();
            System.out.print("请输入新的主演明星:");
            String actor = in.next();
            System.out.print("请输入新的电影票价:");
            double price = in.nextDouble();

            movie.setScore(score);
            movie.setActor(actor);
            movie.setPrice(price);
            System.out.println("电影《"+name+"》信息修改成功！");
        }else {
            System.out.println("未找到该电影，无法修改！");
        }
    }

    private void deleteActor() {
        System.out.print("请输入要封杀的明星姓名:");
        String actor = in.next();
        List<Movie> toRemove=new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getActor().equals(actor)) {
                toRemove.add(movie);
            }
        }
        if(toRemove.isEmpty()){
            System.out.println("未找到该明星主演的电影，封杀失败！");
        }else {
            movies.removeAll(toRemove);
            System.out.println("明星"+actor+"封杀成功，相关电影已下架！");
        }
    }

    private void removeMovie() {
        System.out.print("请输入要下架的电影名称:");
        String name = in.next();
        Movie movie=findMovieByName(name);
        if(movie!=null){
            movies.remove(movie);
            System.out.println("电影《"+name+"》下架成功！");
        }else {
            System.out.println("未找到该电影，无法下架！");
        }
    }

    private void queryMovie() {
        System.out.print("请输入要查询的电影名称:");
        String name = in.next();
        Movie movie=findMovieByName(name);
        if(movie!=null){
            System.out.println("电影信息如下:");
            System.out.println(movie);
        }else {
            System.out.println("未找到该电影！");
        }
    }

    private Movie findMovieByName(String name) {
        for (Movie movie : movies) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    private void addMovie() {
        System.out.print("请输入电影名称:");
        String name = in.next();
        System.out.print("请输入电影评分:");
        String score = in.next();
        System.out.print("请输入主演明星:");
        String actor = in.next();
        System.out.print("请输入电影票价:");
        double price = in.nextDouble();

        Movie movie = new Movie(name, score, actor, price);
        movies.add(movie);
        System.out.println("电影《" + name + "》上架成功！");
    }
}
