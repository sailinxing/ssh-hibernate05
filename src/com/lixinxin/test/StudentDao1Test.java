package com.lixinxin.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lixinxin.dao.StudentDao1;
import com.lixinxin.model.Student;

public class StudentDao1Test {
	private StudentDao1 sdao;
	@Before
	public void init(){
		sdao=new StudentDao1();
	}
	@Test
	public void testSelectById() {
		List<Student> stu = sdao.selectById(1);
		System.out.println(stu);
	}
	@Test
	public void testSelectAll() {
		List<Student> list= sdao.selectAll();
		System.out.println(list);
	}
	@Test
	public void testSelectByItem() {
		List<Student> list= sdao.selectByItem();
		System.out.println(list);
	}
	@Test
	public void testSelectByItem1() {
		List<Student> list= sdao.selectByItem1();
		System.out.println(list);
	}
	@Test
	public void testSelectByOrder() {
		List<Student> list= sdao.selectByOrder();
		System.out.println(list);
	}
	@Test
	public void testSelectByPage() {
		List<Student> list= sdao.selectByPage();
		System.out.println(list);
	}
	@Test
	public void testSelectByInnerJoin() {
		List<Student> list= sdao.selectByInnerJoin();
		System.out.println(list);
	}
	@Test
	public void testSelectByOutterJoin() {
		List<Student> list= sdao.selectByOutterJoin();
		System.out.println(list);
	}
	@Test
	public void testSelectByExample() {
		List<Student> list= sdao.selectByExample();
		System.out.println(list);
	}
	@Test
	public void testSelectByProjection() {
		List<Object> list= sdao.selectByProjection();
			System.out.println(list);
		}
	@Test
	public void testSelectByProjection1() {
		List<Object[]> list= sdao.selectByProjection1();
		for(Object[] objs:list){
			for(Object obj:objs){
				System.out.print(obj+"   ");	
			}
			System.out.println();
		}
		
	}
	@Test
	public void testSelectByGroup() {
		List<Object[]> list= sdao.selectByGroup();
		for(Object[] objs:list){
			for(Object obj:objs){
				System.out.print(obj+"   ");	
			}
			System.out.println();
		}
		
	}
	@Test
	public void testSelectByDetached() {
		List<Object[]> list= sdao.selectByDetached();
		for(Object[] objs:list){
			for(Object obj:objs){
				System.out.print(obj+"   ");	
			}
			System.out.println();
		}
		
	}

}
