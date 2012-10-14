package drsy.weather.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import drsy.weather.data.Network;
import drsy.weather.util.HibernateUtil;
import drsy.weather.util.getppl;

public class Populate extends testQuery {

	public static void main(String[] args) {
		System.out.println("Loading data to pplhib");
		getppl gen = new getppl();
		int id = 123;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Network p = gen.getCountry(id);
		session.save(p);
	}

	/*public void bulkGenerate(int count, String...i1d) {
        String id = i1d.toString();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			getppl gen = new getppl();
			Transaction tx = session.beginTransaction();
			for (int n = 0; n < count; n++) {
				
				Country p = gen.getCountry(id);
				session.save(p);
				if (n % 100 == 0) {
					session.flush();
					session.clear();
				}
			}
			
			for ( int n = 0 ;n < 5 ; n++){
				Country fm = new Country();
				session.save(fm);
			}
			
			tx.commit();
		} finally {
			// session.close();
		}
	}*/
}
