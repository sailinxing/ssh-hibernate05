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

public class StudentDao1 {
	public List<Student> selectById(Integer id) {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("selectById");
			query.setInteger(0,id);
			@SuppressWarnings("unchecked")
			List<Student> list = query.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectAll() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByItem() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			Criterion criterion=Restrictions.eq("name", "jack");
			cri.add(criterion);			
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByItem1() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			Criterion criterion1=Restrictions.ilike("name", "j%");
			Criterion criterion2 = Restrictions.gt("age", 25);
			Criterion c=Restrictions.and(criterion1, criterion2);
			cri.add(c);			
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByOrder() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			Criterion criterion1=Restrictions.ilike("name", "j%");
			Criterion criterion2 = Restrictions.gt("age", 25);
			Criterion c=Restrictions.or(criterion1, criterion2);
			cri.add(c);
			Order o1 = Order.desc("age");
			Order o2 = Order.asc("sid");
			cri.addOrder(o1);	
			cri.addOrder(o2);
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByPage() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class);
			cri.setFirstResult(0);
			cri.setMaxResults(3);
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByInnerJoin() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
//			Criteria cri = session.createCriteria(Student.class,"s")
//					.add(Restrictions.eq("name", "jack"))
//					.createCriteria("course", "c")
//					.add(Restrictions.eq("name", "JAVAWEB"));
//			Criteria cri = session.createCriteria(Student.class,"s")					
//					.createCriteria("course", "c")
//					.add(Restrictions.eq("s.name", "jack"))					
//					.add(Restrictions.eq("c.name", "JAVAWEB"));
//			Criteria cri = session.createCriteria(Student.class,"s")
//					.add(Restrictions.eq("s.name", "jack"))
//					.createAlias("course", "c")
//					.add(Restrictions.eq("c.name", "JAVAWEB"));
			Criteria cri = session.createCriteria(Student.class,"s")
					.createAlias("course", "c")
					.add(Restrictions.eq("s.name", "jack"))					
					.add(Restrictions.eq("c.name", "JAVAWEB"));
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByOutterJoin() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setFetchMode("course", FetchMode.JOIN);	
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Student> selectByExample() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Student stu=new Student();
			stu.setName("jack");
			stu.setAge(18);
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.add(Example.create(stu));
			@SuppressWarnings("unchecked")
			List<Student> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Object> selectByProjection() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setProjection(Property.forName("name"));
			@SuppressWarnings("unchecked")
			List<Object> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Object[]> selectByProjection1() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setProjection(Projections.projectionList()
					.add(Property.forName("name"))
					.add(Property.forName("age")));
			@SuppressWarnings("unchecked")
			List<Object[]> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Object[]> selectByGroup() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria cri = session.createCriteria(Student.class,"s");
			cri.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("age"))
					.add(Projections.rowCount())
					.add(Projections.avg("age")));
			@SuppressWarnings("unchecked")
			List<Object[]> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
	public List<Object[]> selectByDetached() {
		Session session = DBUtils.findSession();
		Transaction tx = session.beginTransaction();
		try {
			DetachedCriteria dt = DetachedCriteria.forClass(Student.class);
			dt.setProjection(Projections.projectionList()
					.add(Projections.groupProperty("age"))
					.add(Projections.rowCount())
					.add(Projections.avg("age")));
			Criteria cri = dt.getExecutableCriteria(session);
			@SuppressWarnings("unchecked")
			List<Object[]> list= cri.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		return null;
	}
}
