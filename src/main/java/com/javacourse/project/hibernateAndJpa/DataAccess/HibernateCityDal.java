package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.Entities.City;

//JPA - ORM
@Repository
public class HibernateCityDal implements ICityDal {
	
	private EntityManager entityManager;

	@Autowired //ctor injection yaptık. autowired ise şu; bu entitymanagerin karşığının ne olduğuna paketlerden bak. bizde hibernate var, otomatik olarak hibernate injection yapacak.  
	public HibernateCityDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

//AOP - Aspect Oriented Programming
	// kod build olduğunda bunun önüne ve arkasına açma ve kapama kodlarını otomatik koyuyor. ve baya işi rahatlatıyor.
	@Override
	@Transactional
	public List<City> GetAll() {
		Session session = entityManager.unwrap(Session.class); // spring framework hibernatele ilgili olan bütün injectionları yaptı.
		List<City> cities = session.createQuery("from City",City.class).getResultList();
		return cities;
	}

	@Override
	@Transactional
	public void add(City city) {
		//id 0dan farklıysa gibi kontrol yapılabilir.
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city); //id vermezsek ekleyecek, verirsek güncelleyecek
		
	}

	@Override
	@Transactional
	public void update(City city) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
	}

	@Override
	@Transactional
	public void delete(City city) {
		Session session = entityManager.unwrap(Session.class);
		City cityToDelete = session.get(City.class, city.getId());
		session.delete(cityToDelete);
		
	}

	@Override
	public City getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		City city = session.get(City.class, id);
		return city;
	}

}
