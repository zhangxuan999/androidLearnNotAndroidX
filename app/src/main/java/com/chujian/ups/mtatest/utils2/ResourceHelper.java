package com.chujian.ups.mtatest.utils2;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * 资源反射获取方式
 * @author xiaohei
 *
 */
public class ResourceHelper {
	
	public static void showTip(Context context, String resID){
		Toast.makeText(context, getString(context,resID), Toast.LENGTH_SHORT).show();
	}
	
	public static void showTipStr(Context context, String txt){
		Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
	}
	
	public static Drawable getDrawable(Context context, String resID){
		
		return context.getResources().getDrawable(getIdentifier(context, resID));
	}
	
	public static String getString(Context context, String resID){
		
		return context.getResources().getString(getIdentifier(context, resID));
	}
	
	public static View getView(Activity context, String resID){
		
		return context.findViewById(getIdentifier(context, resID));
	}
	
	public static View getViewByParent(View view, String resID){
		
		return view.findViewById(getIdentifier(view.getContext(), resID));
	}
	
	public static View getViewByWindow(Window view, String resID){
		
		return view.findViewById(getIdentifier(view.getContext(), resID));
	}

	/**
	 * 获取除了styleable类型之外的资源ID
	 * 该方法无法获取styleable类型的资源ID
	 * @param context
	 * @param paramString
	 * @return
	 */
	public static int getIdentifier(Context context, String paramString){
		if(paramString != null){
			String[] splits = paramString.split("\\.", 3);
			if(splits.length == 3){
				return context.getResources().getIdentifier(splits[2], splits[1], context.getPackageName());
			}
		}
		
		return 0;
	}
	
	/**
	 * 获取资源ID
	 * 一般只用该方法获取styleable类型的资源id
	 * @param context
	 * @param name
	 * @return
	 */
	public static int getResource(Context context, String name){

		String[] splits = name.split("\\.", 3);
		if(splits.length == 3){
			Object obj = getResourceId(context, splits[2], splits[1]);
			
			return obj == null ? 0 : (Integer)obj;
		}

		return 0;
	}
	
	/***
	 * 获取资源ID数组
	 * 一般只用该方法获取styleable类型的资源数组
	 * @param context
	 * @param name
	 * @return
	 */
	public static int[] getResouceArray(Context context, String name){
		String[] splits = name.split("\\.", 3);
		if(splits.length == 3){
			Object obj = getResourceId(context, splits[2], splits[1]);
			
			return obj == null ? new int[0] :(int[])obj;
		}
		
		return new int[0];
	}
	
	/**
	 * 纯粹反射获取资源id，比如getResource无法获取styleable类型的资源id和数组。采用这种方式获取
	 * @param context
	 * @param name
	 * @param type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Object getResourceId(Context context, String name, String type){
		String className = context.getPackageName()+".R";
		try{
			
			Class cls = Class.forName(className);
			for(Class child : cls.getClasses()){
				String sname = child.getSimpleName();
				if(sname.equals(type)){
					for(Field f : child.getFields()){
						String fname = f.getName();
						if(fname.equals(name)){
							return f.get(null);
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
