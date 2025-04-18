import org.example.Circle;
import org.example.Point;

import static org.example.Client.isInCircle;


public class Test {
    @org.junit.Test
    public void test01() {
        //对ex01中的函数进行测试
        Point o1 = new Point();
        Point o2 = new Point();
        o1.setXY(0, 0);
        o2.setXY(5, 0);
        Circle m = new Circle();
        m.setCenter(o1);
        m.setR(-5);
        isInCircle(m, o2);
    }
    @org.junit.Test
    public void test02() {
        Point o1 = new Point();
        Point o2 = new Point();
        o1.setXY(0, 0);
        o2.setXY(5, 0);
        Circle m = new Circle();
        m.setCenter(o1);
        m.setR(5);
        isInCircle(m, o2);
    }
    @org.junit.Test
    public void test03() {
        Point o1 = new Point();
        Point o2 = new Point();
        o1.setXY(0, 0);
        o2.setXY(5, 0);
        Circle m = new Circle();
        m.setCenter(o1);
        m.setR(7);
        isInCircle(m, o2);
    }
    @org.junit.Test
    public void test04() {
        Point o1 = new Point();
        Point o2 = new Point();
        o1.setXY(0, 0);
        o2.setXY(5, 0);
        Circle m = new Circle();
        m.setCenter(o1);
        m.setR(3);
        isInCircle(m, o2);
    }
    @org.junit.Test
    public void test05() {
        Point o1 = new Point();
        Point o2 = new Point();
        o1.setXY(0, 0);
        o2.setXY(5, 0);
        Circle m = new Circle();
        m.setCenter(o1);
        m.setR(0);
        isInCircle(m, o2);
    }
}
