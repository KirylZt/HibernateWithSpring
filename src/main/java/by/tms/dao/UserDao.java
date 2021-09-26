package by.tms.dao;

import by.tms.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user){
		Session session = sessionFactory.openSession();
		session.save(user);
		session.close();
	}

	@Transactional
	public void update(User user){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		User user1 = session.get(User.class, user.getId());
		user1.setName(user.getName());
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		session.merge(user1);
		transaction.commit();
		transaction.rollback();
		session.close();
	}

	public void delete(User user){
		Session session = sessionFactory.openSession();
		session.delete(user);
		session.close();
	}

	public void deleteById(long id){
		Session session = sessionFactory.openSession();
		User user = session.get(User.class, id);
		session.delete(user);
		session.close();
	}

	public List<User> findAll(){
		Session session = sessionFactory.openSession();
		Query<User> query = session.createQuery("from User", User.class);
		return query.getResultList();
	}

	public User findByUsername(String username){
		Session session = sessionFactory.openSession();
		Query<User> query = session.createQuery("from User where username = :username", User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}

}
