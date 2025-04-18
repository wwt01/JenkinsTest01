package org.example;

// 定义 ex01.Circle 类
public class Circle {
    private Point o;
    private int r;

    // 设置半径
    public void setR(int c) {
        r = c;
    }

    // 获取半径
    public int getR() {
        return r;
    }

    // 设置圆心
    public void setCenter(Point a) {
        o = a;
    }

    // 获取圆心
    public Point getCenter() {
        return o;
    }
}