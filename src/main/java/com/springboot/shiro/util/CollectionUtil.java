package com.springboot.shiro.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集合运算
 * 
 * @author ztgreat
 *
 */
public class CollectionUtil {

	/**
	 * 两个集合的交集
	 * 
	 * @param ls
	 * @param ls2
	 * @return
	 */
	public static <T> Set<T> intersect(List<T> ls, List<T> ls2) {
		Set<T> result = new HashSet<T>(ls);
		result.retainAll(ls2);
		return result;
	}

	/**
	 * 两个集合的并集
	 * 
	 * @param ls
	 * @param ls2
	 * @return
	 */
	public static <T> Set<T> union(List<T> ls, List<T> ls2) {
		Set<T> result = new HashSet<T>(ls);
		result.addAll(ls2);
		return result;
	}

	/**
	 * 集合1-集合2 的内容（差集，注意集合顺序）
	 * 
	 * @param ls
	 * @param ls2
	 * @return
	 */
	public static <T> Set<T> diff(List<T> ls, List<T> ls2) {
		Set<T> result = new HashSet<T>(ls);
		result.removeAll(ls2);
		return result;
	}

}
