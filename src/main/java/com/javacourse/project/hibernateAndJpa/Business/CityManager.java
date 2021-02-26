package com.javacourse.project.hibernateAndJpa.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.DataAccess.ICityDal;
import com.javacourse.project.hibernateAndJpa.Entities.City;

@Service
public class CityManager implements ICityService {

	
	private ICityDal cityDal;
	
	@Autowired
	public CityManager(ICityDal cityDal) {
		this.cityDal = cityDal;
	}
	

	@Override
	@Transactional
	public List<City> GetAll() {
		return this.cityDal.GetAll();
	}

	@Override
	@Transactional
	public void add(City city) {
		//eklenecek şehrin daha önce eklenmemesi lazım gibi business kuralları yazılır.
		this.cityDal.add(city);
		
	}

	@Override
	@Transactional
	public void update(City city) {
		this.cityDal.update(city);
		
	}

	@Override
	@Transactional
	public void delete(City city) {
		this.cityDal.delete(city);
		
	}


	@Override
	@Transactional
	public City getById(int id) {
		return this.cityDal.getById(id);
	}

}
