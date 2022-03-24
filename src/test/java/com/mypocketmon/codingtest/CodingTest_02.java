package com.mypocketmon.codingtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CodingTest_02 {
	
	@Test
	void 코딩테스트_01(){
		// 짝수개의 수 묶음 중에서 하나가 빠졌을 때 그 빠진 수 구하기
		// input : int[] numbers
		//int[] numbers = {1,3,11,2,3,4,4,9,5,2,5,6,6,1,7,7,8,9,10,11,10};	// 8빠짐
		int[] numbers = {1,3,11,2,3,4,4,9,5,2,5,6,6,1,7,8,7,8,9,27,10,11,10,45,34,45,34,27,25,25,112,112,82};	// 82빠짐
		int result = 0;
		
		List<Integer> numberList = Arrays.stream(numbers)
												.boxed()
												.sorted()
												.collect(Collectors.toList());
		for(int i = 0; i<numberList.size(); i++) {
			if(i%2==0) {
				if(numberList.get(i) != numberList.get(i+1)){
					result = numberList.get(i);
					break;
				}
			}
		}
		System.out.println(result);
	}	
	
	
	@Test
	void 코딩테스트_02(){
		// 짝수개의 수 묶음 중에서 하나가 빠졌을 때 그 빠진 수 구하기
		// input : String[] numbers
		String[] numbers = {"1","3","11","2","3","4","2","1","4"};	// 11빠짐
		int result = 0;
		
		List<String> numberList = Arrays.asList(numbers).stream()
												.sorted()
												.collect(Collectors.toList());
		for(int i = 0; i<numberList.size(); i++) {
			if(i%2==0) {
				if(!numberList.get(i).equals(numberList.get(i+1))){
					result = Integer.parseInt(numberList.get(i));
					break;
				}
			}
		}
		System.out.println(result);
	}	
}
