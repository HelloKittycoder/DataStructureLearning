/**
 * Created by shucheng on 2019-5-27 下午 20:25
 */
public class Student {

    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s,score: %d)", name, score);
    }
}
