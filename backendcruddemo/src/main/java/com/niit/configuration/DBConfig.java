package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.dao.PersonDao;
import com.niit.dao.PersonDaoImpl;
import com.niit.model.Person;




public class DBConfig

{
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("site");
		dataSource.setPassword("site");

		System.out.println("DataBase is connected.........!");
		return dataSource;

	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		System.out.println("Hibernate Properties");
		return properties;

	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClasses(Person.class);
	
		/*  sessionBuilder.addAnnotatedClasses(Blog.class);
		  sessionBuilder.addAnnotatedClasses(Friend.class);
		  sessionBuilder.addAnnotatedClasses(Job.class);
		  sessionBuilder.addAnnotatedClasses(Forum.class);
		  sessionBuilder.addAnnotatedClasses(ForumComment.class);
		 sessionBuilder.addAnnotatedClasses(BlogLikes.class);*/
		 
		System.out.println("Session is crated................!");

		return sessionBuilder.buildSessionFactory();

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("Transaction is crated............!");
		return transactionManager;
	}
	
	@Autowired
	@Bean(name="personDao")
	public PersonDao getpersonDAO(SessionFactory sessionFactory){
		
		return new PersonDaoImpl(sessionFactory);
	}


	
}