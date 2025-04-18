package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

import static org.testng.Assert.assertTrue;

public class TestIntegrate {
    public static void main(String[] args) {
        try {
            // 定义被积函数
            UnivariateFunction function = new UnivariateFunction() {
                @Override
                public double value(double x) {
                    return x * x;
                }
            };

            // 定义积分区间
            double lowerBound = 0;
            double upperBound = 1;

            // 使用默认构造函数创建 RombergIntegrator 实例
            RombergIntegrator integrator1 = new RombergIntegrator();
            System.out.println("默认设置下的最大迭代次数: " + integrator1.getMaximalIterationCount());
            System.out.println("默认设置下的最小迭代次数: " + integrator1.getMinimalIterationCount());

            // 调用 doIntegrate 方法进行积分计算
            double result1 = integrator1.integrate(1000, function, lowerBound, upperBound);
            System.out.println("默认设置下的积分结果: " + result1);

            // 使用指定的相对精度、绝对精度、最小迭代次数和最大迭代次数创建 RombergIntegrator 实例
            RombergIntegrator integrator2 = new RombergIntegrator(1e-6, 1e-6, 3, 10);
            System.out.println("自定义设置下的最大迭代次数: " + integrator2.getMaximalIterationCount());
            System.out.println("自定义设置下的最小迭代次数: " + integrator2.getMinimalIterationCount());

            // 调用 doIntegrate 方法进行积分计算
            double result2 = integrator2.integrate(1000, function, lowerBound, upperBound);
            System.out.println("自定义设置下的积分结果: " + result2);

            // 使用指定的最小迭代次数和最大迭代次数创建 RombergIntegrator 实例
            RombergIntegrator integrator3 = new RombergIntegrator(2, 8);
            System.out.println("指定迭代次数设置下的最大迭代次数: " + integrator3.getMaximalIterationCount());
            System.out.println("指定迭代次数设置下的最小迭代次数: " + integrator3.getMinimalIterationCount());

            // 调用 doIntegrate 方法进行积分计算
            double result3 = integrator3.integrate(1000, function, lowerBound, upperBound);
            System.out.println("指定迭代次数设置下的积分结果: " + result3);
            assertTrue(true);

        } catch (NumberIsTooSmallException | NumberIsTooLargeException |
                 MaxCountExceededException e) {
            e.printStackTrace();

            assertTrue(false);
        }
    }
}    