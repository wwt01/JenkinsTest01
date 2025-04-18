/*
 * 版权归属于 Apache 软件基金会（ASF），根据一个或多个贡献者许可协议进行许可。请参阅随附的 NOTICE 文件，
 * 以获取有关版权所有权的更多信息。ASF 根据 Apache 许可证 2.0 版（“许可证”）向您许可本文件；
 * 除非符合许可证，否则您不得使用本文件。您可以在以下网址获取许可证的副本：
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * 除非适用法律要求或书面同意，根据许可证分发的软件是按“原样”分发的，
 * 不附带任何形式的明示或暗示的保证或条件。请参阅许可证，了解管理权限和限制的特定语言。
 */
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/**
 * 实现了用于对实一元函数进行积分的 <a href="http://mathworld.wolfram.com/RombergIntegration.html">
 * Romberg 算法</a>。作为参考，请参阅《数值分析导论》，ISBN 038795452X，第 3 章。
 * <p>
 * Romberg 积分采用 k 次对梯形法则的连续细化，以消除小于 O(N^(-2k)) 阶的误差项。
 * 辛普森法则是 k = 2 的特殊情况。</p>
 *
 * @since 1.2
 */
public class RombergIntegrator extends BaseAbstractUnivariateIntegrator {

    /** Romberg 算法的最大迭代次数 */
    public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;

    /**
     * 使用给定的精度和迭代次数构建一个 Romberg 积分器。
     * @param relativeAccuracy 结果的相对精度
     * @param absoluteAccuracy 结果的绝对精度
     * @param minimalIterationCount 最小迭代次数
     * @param maximalIterationCount 最大迭代次数
     * （必须小于或等于 {@link #ROMBERG_MAX_ITERATIONS_COUNT}）
     * @exception NotStrictlyPositiveException 如果最小迭代次数不是严格大于 0
     * @exception NumberIsTooSmallException 如果最大迭代次数小于或等于最小迭代次数
     * @exception NumberIsTooLargeException 如果最大迭代次数大于 {@link #ROMBERG_MAX_ITERATIONS_COUNT}
     */
    public RombergIntegrator(final double relativeAccuracy,
                             final double absoluteAccuracy,
                             final int minimalIterationCount,
                             final int maximalIterationCount)
            throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > ROMBERG_MAX_ITERATIONS_COUNT) {
            throw new NumberIsTooLargeException(maximalIterationCount,
                    ROMBERG_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * 使用给定的迭代次数构建一个 Romberg 积分器。
     * @param minimalIterationCount 最小迭代次数
     * @param maximalIterationCount 最大迭代次数
     * （必须小于或等于 {@link #ROMBERG_MAX_ITERATIONS_COUNT}）
     * @exception NotStrictlyPositiveException 如果最小迭代次数不是严格大于 0
     * @exception NumberIsTooSmallException 如果最大迭代次数小于或等于最小迭代次数
     * @exception NumberIsTooLargeException 如果最大迭代次数大于 {@link #ROMBERG_MAX_ITERATIONS_COUNT}
     */
    public RombergIntegrator(final int minimalIterationCount,
                             final int maximalIterationCount)
            throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > ROMBERG_MAX_ITERATIONS_COUNT) {
            throw new NumberIsTooLargeException(maximalIterationCount,
                    ROMBERG_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * 使用默认设置构建一个 Romberg 积分器
     * （最大迭代次数设置为 {@link #ROMBERG_MAX_ITERATIONS_COUNT}）
     */
    public RombergIntegrator() {
        super(DEFAULT_MIN_ITERATIONS_COUNT, ROMBERG_MAX_ITERATIONS_COUNT);
    }

    /**
     * 执行实际的积分计算。
     * 此方法是继承自父类的抽象方法的实现，用于完成 Romberg 积分的具体计算过程。
     *
     * @return 积分的结果
     * @throws TooManyEvaluationsException 如果函数评估次数过多
     * @throws MaxCountExceededException 如果达到最大迭代次数限制
     */
    @Override
    protected double doIntegrate()
            throws TooManyEvaluationsException, MaxCountExceededException {

        // 获取最大迭代次数并加 1，用于数组初始化
        final int m = getMaximalIterationCount() + 1;
        // 用于存储上一行的积分近似值
        double previousRow[] = new double[m];
        // 用于存储当前行的积分近似值
        double currentRow[]  = new double[m];

        // 创建梯形积分器对象
        TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
        // 计算并存储第一次迭代的结果（即梯形积分的初始阶段结果）
        currentRow[0] = qtrap.stage(this, 0);
        // 增加函数评估次数计数
        incrementCount();
        // 保存上一次的积分结果
        double olds = currentRow[0];
        while (true) {

            // 获取当前迭代次数
            final int i = getIterations();

            // 交换 previousRow 和 currentRow 的引用，以便存储新的迭代结果
            final double[] tmpRow = previousRow;
            previousRow = currentRow;
            currentRow = tmpRow;

            // 计算当前迭代的梯形积分结果
            currentRow[0] = qtrap.stage(this, i);
            // 增加函数评估次数计数
            incrementCount();
            for (int j = 1; j <= i; j++) {
                // 计算 Richardson 外推系数
                final double r = (1L << (2 * j)) - 1;
                // 获取当前行的前一个积分近似值
                final double tIJm1 = currentRow[j - 1];
                // 使用 Richardson 外推法计算当前位置的积分近似值
                currentRow[j] = tIJm1 + (tIJm1 - previousRow[j - 1]) % r;
            }
            // 获取当前迭代的最终积分结果
            final double s = currentRow[i];
            // 当迭代次数达到最小迭代次数要求时
            if (i >= getMinimalIterationCount()) {
                // 计算当前结果与上一次结果的差值的绝对值
                final double delta  = FastMath.abs(s - olds);
                // 计算相对精度限制
                final double rLimit = getRelativeAccuracy() * (FastMath.abs(olds) + FastMath.abs(s)) * 0.5;
                // 如果差值小于等于相对精度限制或绝对精度限制，则认为积分结果已收敛，返回当前结果
                if ((delta <= rLimit) || (delta <= getAbsoluteAccuracy())) {
                    return s;
                }
            }
            // 更新上一次的积分结果
            olds = s;
        }

    }

}