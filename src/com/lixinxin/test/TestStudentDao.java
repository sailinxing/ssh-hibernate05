package com.lixinxin.test;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lixinxin.dao.StudentDao;
import com.lixinxin.model.Student;

public class TestStudentDao {
	private StudentDao sdao;

	@Before
	public void init() {
		sdao = new StudentDao();
	}
	@Test
	public void selectById(){
		Student student = sdao.selectById(1);
		System.out.println(student);
	}
	@Test
	public void selectByAll(){
		List<Student> list = sdao.selectByAll();
		System.out.println(list);
	}
	@Test
	public void selectByCon(){
		List<Student> list = sdao.selectByCon(25);
		System.out.println(list);
	}
	@Test
	public void selectByCon1(){
		List<Student> list = sdao.selectByCon1();
		System.out.println(list);
	}
	@Test
	public void selectByOrder(){
		List<Student> list = sdao.selectByOrder();
		System.out.println(list);
	}
	@Test
	public void selectByPage(){
		List<Student> list = sdao.selectByPage(0, 3);
		System.out.println(list);
	}
	@Test
	public void selectByInnerjoin(){
		List<Student> list = sdao.selectByInnerJoin();
		System.out.println(list);
		for(Student s:list){
			System.out.println(s.getCourse());
		}
	}
	@Test
	public void selectByOuterjoin(){
		List<Student> list = sdao.selectByOutterJoin();
		System.out.println(list);
		for(Student s:list){
			System.out.println(s.getCourse());
		}
	}
	@Test
	public void selectByExample(){
		List<Student> list = sdao.selectByExample();
		System.out.println(list);
	}
	@Test
	public void selectByProjection(){
		List<Object> list = sdao.selectByProjection();
		System.out.println(list);
	}
	@Test
	public void selectByProjection1(){
		List<Object[]> list = sdao.selectByProjection1();
		for(Object[] objs:list){
			for(Object obj:objs){
			System.out.print(obj);
			}
			System.out.println();
		}
	}
	@Test
	public void selectByGroup(){
		List<Object[]> list = sdao.selectByGroup();
		for(Object[] objs:list){
			for(Object obj:objs){
			System.out.print(obj+"    ");
			}
			System.out.println();
		}
	}
	@Test
	public void selectByDetached(){
		List<Object[]> list = sdao.selectByDetached();
		for(Object[] objs:list){
			for(Object obj:objs){
			System.out.print(obj+"    ");
			}
			System.out.println();
		}
	}
}
