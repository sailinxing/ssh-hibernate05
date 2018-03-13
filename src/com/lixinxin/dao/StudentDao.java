package com.lixinxin.dao;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.lixinxin.model.Student;
import com.lixinxin.utils.DBUtils;

public class StudentDao {
	public Student selectById(Integer id){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		Student stu=null;
		try {
			Query query = session.getNamedQuery("selectById");
			query.setInteger(0, id);
		stu= (Student) query.uniqueResult();
		tx.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return stu;
	}
	public List<Student> selectByAll(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByCon(Integer age){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			Criterion criterion=Restrictions.eq("age", age);
			cri= cri.add(criterion);
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByCon1(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			Criterion criterion1=Restrictions.ilike("name", "j%");
			Integer[] ages={25,26};
			Criterion criterion2=Restrictions.in("age", ages);
			Criterion criterion3= Restrictions.or(criterion1, criterion2);
			cri=cri.add(criterion3);
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByOrder(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			Criterion criterion=Restrictions.gt("age", 22);			
			cri= cri.add(criterion);
			Order order1 = Order.asc("age");
			Order order2 = Order.desc("sid");
			cri=cri.addOrder(order1);
			cri=cri.addOrder(order2);
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByPage(Integer f,Integer m){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			cri.setFirstResult(f);
			cri.setMaxResults(m);
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByInnerJoin(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
//			Criteria cri = session.createCriteria(Student.class).createCriteria("course");
			Criteria cri = session.createCriteria(Student.class,"s").createAlias("course", "c");
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByOutterJoin(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setFetchMode("course", FetchMode.JOIN);
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Student> selectByExample(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Student student=new Student();
			student.setName("jack");
			student.setAge(18);
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.add(Example.create(student));
			@SuppressWarnings("unchecked")
			List<Student> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Object> selectByProjection(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setProjection(Property.forName("name"));
			@SuppressWarnings("unchecked")
			List<Object> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Object[]> selectByProjection1(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setProjection(Projections.projectionList()
					.add(Property.forName("name"))
					.add(Property.forName("age")));
			@SuppressWarnings("unchecked")
			List<Object[]> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Object[]> selectByGroup(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setProjection(Projections.projectionList().
					add(Projections.groupProperty("age"))
					.add(Projections.rowCount())
					.add(Projections.avg("age")));
			@SuppressWarnings("unchecked")
			List<Object[]> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
	public List<Object[]> selectByDetached(){
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Student.class);
			dc.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("age"))
					.add(Projections.rowCount())
					.add(Projections.min("age")));
			Criteria cri = dc.getExecutableCriteria(session);
			@SuppressWarnings("unchecked")
			List<Object[]> list = cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null; 
	}
}
