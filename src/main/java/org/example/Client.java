package org.example;

public class Client {
    // 判断点是否在圆内的方法
    public static boolean isInCircle(Circle m1, Point o1) {
        int r = m1.getR();
        if(r<=0)
        {
            System.out.println("半径必须大于0");
            return false;
        }
        int x1 = o1.getX();
        int y1 = o1.getY();
        Point o3 = m1.getCenter();
        int x2 = o3.getX();
        int y2 = o3.getY();
        // 计算两点之间的距离
        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        if (distance > r) {
            System.out.println("点在圆外");
        } else if (distance == r) {
            System.out.println("点在圆上");
        } else {
            System.out.println("点在圆内");
        }
        return true;
    }
}