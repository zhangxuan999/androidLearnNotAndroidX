package com.chujian.ups.mtatest.utils2;

public class Arrays {

	public static boolean contain(Object[] array, Object element){
		if(array == null || array.length == 0){
			return false;
		}
		
		for(Object ele : array){
			if(ele.equals(element)){
				return true;
			}
		}
		
		return false;
	}
	
}
