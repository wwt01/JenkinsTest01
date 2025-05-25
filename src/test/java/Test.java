import org.example.Circle;
import org.example.Point;
import org.junit.Assert;

import static org.example.Client.isInCircle;


public class Test {
    @org.junit.Test
    public void test01() {
        try {
            Point o1 = new Point();
            Point o2 = new Point();
            o1.setXY(0, 0);
            o2.setXY(5, 0);
            Circle m = new Circle();
            m.setCenter(o1);
            m.setR(-5);//半径输入小于0
            boolean result = isInCircle(m, o2);
            // 这里可根据实际情况修改断言逻辑，假设半径为负时不应正常返回结果
            Assert.fail("半径为负时不应该正常执行，预期抛出异常");
        } catch (Exception e) {
            // 捕获到异常说明符合预期
        }
    }
    @org.junit.Test
    public void test02() {
        try {
            Point o1 = new Point();
            Point o2 = new Point();
            o1.setXY(0, 0);
            o2.setXY(5, 0);
            Circle m = new Circle();
            m.setCenter(o1);
            m.setR(5);
            boolean result = isInCircle(m, o2);
            // 根据实际情况修改断言条件
            Assert.assertTrue(result);
        } catch (Exception e) {
            Assert.fail("测试过程中抛出异常: " + e.getMessage());
        }
    }

    @org.junit.Test
    public void test03() {
        try {
            Point o1 = new Point();
            Point o2 = new Point();
            o1.setXY(0, 0);
            o2.setXY(5, 0);
            Circle m = new Circle();
            m.setCenter(o1);
            m.setR(7);
            boolean result = isInCircle(m, o2);
            // 根据实际情况修改断言条件
            Assert.assertTrue(result);
        } catch (Exception e) {
            Assert.fail("测试过程中抛出异常: " + e.getMessage());
        }
    }

    @org.junit.Test
    public void test04() {
        try {
            Point o1 = new Point();
            Point o2 = new Point();
            o1.setXY(0, 0);
            o2.setXY(5, 0);
            Circle m = new Circle();
            m.setCenter(o1);
            m.setR(3);
            boolean result = isInCircle(m, o2);
            // 根据实际情况修改断言条件
            Assert.assertFalse(result);
        } catch (Exception e) {
            Assert.fail("测试过程中抛出异常: " + e.getMessage());
        }
    }

    @org.junit.Test
    public void test05() {
        try {
            Point o1 = new Point();
            Point o2 = new Point();
            o1.setXY(0, 0);
            o2.setXY(5, 0);
            Circle m = new Circle();
            m.setCenter(o1);
            m.setR(0);
            boolean result = isInCircle(m, o2);
            // 根据实际情况修改断言条件
            Assert.assertFalse(result);
        } catch (Exception e) {
            Assert.fail("测试过程中抛出异常: " + e.getMessage());
        }
    }
}
