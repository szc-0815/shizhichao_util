package com.shizhichao.utils;

import java.util.Random;

//随机工具类
public class RandomUtil {

	/**
	 * @Title: random   
	 * @Description: 获得最小数和最大数之间的随机数   
	 * @param: @param min
	 * @param: @param max
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int random(int min,int max) {
		Random random = new Random();
		return min+random.nextInt(max-min+1);
	}
	/**
	 * @Title: random   
	 * @Description: 获得最小数和最大数之间的多个随机数
	 * @param: @param min
	 * @param: @param max
	 * @param: @param num
	 * @param: @return      
	 * @return: int[]      
	 * @throws
	 */
	public static int[] random(int min,int max,int num) {
		int[] intArray = new int[num];
		for (int i = 0; i < num; i++) {
			intArray[i] = random(min, max);
		}
		return intArray;
	}

}
