package drsy.weather.app;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;

import drsy.weather.data.Country;
import drsy.weather.data.Network;
import drsy.weather.data.Station;
import drsy.weather.data.Wdata;
import drsy.weather.data.WdataKey;
import drsy.weather.util.HibernateUtil;

public class testQuery {

	public Network find(int id) {
		if (id == 0)
			return null;

		Network r = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			r = (Network) session.load(Network.class, id);

			// forcing the proxy to load contacts - this not good as it costs us
			// another trip to the database
			if (r != null)
				r.getName();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;
	}

	public Station find(String id) {
		if (id.isEmpty())
			return null;

		Station r = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			r = (Station) session.load(Station.class, id);

			// forcing the proxy to load contacts - this not good as it costs us
			// another trip to the database
			if (r != null)
				r.getName();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;
	}

	public Wdata find(WdataKey id) {
		if (id == null)
			return null;

		Wdata r = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			r = (Wdata) session.load(Wdata.class, id);

			// forcing the proxy to load contacts - this not good as it costs us
			// another trip to the database
			if (r != null)
				r.getAlti();

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;
	}
	
	public ArrayList<Wdata> find(Wdata template) {

		ArrayList<Wdata> r = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// since the data is being detached we must fetch eagerly
			Criteria c = session.createCriteria(Wdata.class);
			c.setFetchMode("station", FetchMode.JOIN);

			if (template.getStation() != null) {
				WdataKey key = new WdataKey();
				key.setDate(template.getDate());
				key.setStation(template.getStation());
				key.setTime(template.getTime());
				c.add(Restrictions.idEq(key));
				c.setMaxResults(1);
//			} else {
//				if (template.getLastName() != null)
//					c.add(Restrictions.like("firstname", template.getFirstName()));
//
//				if (template.getLastName() != null)
//					c.add(Restrictions.like("lastname", template.getLastName()));
//
//				if (template.getRole() != null)
//					c.add(Restrictions.like("role", template.getRole()));
//
//				if (template.getNickName() != null)
//					c.add(Restrictions.like("nickname", template.getNickName()));
			}

			//System.out.println("find() " + c.toString());

			// the join creates duplicate records - this will remove them
			Set<Wdata> set = new LinkedHashSet<Wdata>(c.list());
			r = new ArrayList<Wdata>(set);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;	
	}

	
	public List<Wdata> find(float alti) {
		if (alti == 0.0)
			return null;

		List<Wdata> r = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Wdata as p where p.alti = :alti");
			query.setDouble("alti", alti);

			// the join creates duplicate records - this will remove them
			Set<Wdata> set = new LinkedHashSet<Wdata>(query.list());
			r = new ArrayList<Wdata>(set);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;	
	}

	
	/**
	 * here we use a class to act as a template for what we are searching for.
	 * Demonstrates the Criteria type of searching. The nice feature of Criteria
	 * is the ability for some compile-time checking whereas, HQL is all runtime
	 * checking
	 * 
	 * @param template
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Station> find(Station template) {
		ArrayList<Station> r = null;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// since the data is being detached we must fetch eagerly
			Criteria c = session.createCriteria(Station.class);
			//c.setFetchMode("contacts", FetchMode.JOIN);

			//if (template.getId() != null)
			{
				c.add(Restrictions.idEq(template.getId()));
				c.setMaxResults(10);
			} 
			//else
			{
				/*if (template.getLastName() != null)
					c.add(Restrictions.like("firstname", template.getFirstName()));

				if (template.getLastName() != null)
					c.add(Restrictions.like("lastname", template.getLastName()));

				if (template.getRole() != null)
					c.add(Restrictions.like("role", template.getRole()));

				if (template.getNickName() != null)
					c.add(Restrictions.like("nickname", template.getNickName()));*/
			}

			//System.out.println("find() " + c.toString());

			// the join creates duplicate records - this will remove them
			Set<Station> set = new LinkedHashSet<Station>(c.list());
			r = new ArrayList<Station>(set);

			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;
	}

	public ArrayList<Wdata> averageTemperature(WdataKey key) {
		if(key == null) {
			return null;
		}
		
		ArrayList<Wdata> r =  null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			//Station s = find(id);
			Query query = session.createQuery("select p.tmpf from Wdata as p where p.station = :station and p.date = :date");
			query.setInteger("date", key.getDate());
			query.setString("station", key.getStation());

			// the join creates duplicate records - this will remove them
			Set<Wdata> set = new LinkedHashSet<Wdata>(query.list());
			r = new ArrayList<Wdata>(set);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return r;
	}
	
	public void showStats() {
		try {
			Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
			double queryCacheHitCount = stats.getQueryCacheHitCount();
			double queryCacheMissCount = stats.getQueryCacheMissCount();
			double queryCacheHitRatio = queryCacheHitCount / (queryCacheHitCount + queryCacheMissCount);
			System.out.println("--> Query Hit ratio: " + queryCacheHitRatio);

			System.out.println("--> TX count: " + stats.getTransactionCount());

			EntityStatistics entityStats = stats.getEntityStatistics(Country.class.getName());
			long changes = entityStats.getInsertCount() + entityStats.getUpdateCount() + entityStats.getDeleteCount();
			System.out.println("--> " + Country.class.getName() + " changed " + changes + " times");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
