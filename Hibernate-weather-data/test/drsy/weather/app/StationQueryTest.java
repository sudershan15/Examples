package drsy.weather.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import drsy.weather.data.Network;
import drsy.weather.data.Station;
import drsy.weather.data.Wdata;
import drsy.weather.data.WdataKey;

public class StationQueryTest {

	@Before
	public void before() {
		System.out.println("\n-----------------------------------------------------");
	}
	
	@Test
	public void testCriteria() {
		testQuery ppl = new testQuery();
		String id = "059ZX";
		Station template = new Station();
		template.setId(id);
		List<Station> list = ppl.find(template);
		Assert.assertNotNull("List NULL!! :(", list);
		System.out.println("\nfound " + list.size() + " results");
		for (Station p : list)
			System.out.println(p);
	}
	
	@Test
	public void testQueryHQL() {
		testQuery ppl = new testQuery();
		Network list = ppl.find(10);
		Assert.assertNotNull(list);
		System.out.println(list.getName());
	}
	
	@Test
	@Ignore
	public void testWdata() {
		testQuery ppl = new testQuery();
		float id = -9999;
		List<Wdata> list = ppl.find(id);
		Assert.assertNotNull(list);
		System.out.println("\nfound " + list.size() + " results");
//		for (Wdata p : list)
//			System.out.println(p.getNetwork());
	}
	
	@Test
	public void testWdatakey() {
		WdataKey key = new WdataKey();
		testQuery ppl = new testQuery();
		int date = 20121005;
		String station = "DKRM8";
		int time = 1215;
		key.setDate(date);
		key.setStation(station);
		key.setTime(time);
		Wdata list = ppl.find(key);
		Assert.assertNotNull(list);
		System.out.println("\nfound results");
		System.out.println(list.getNetwork() + "    " + list.getStation());
	}
	
	@Test
	@Ignore
	public void testaverageTemp() {
		WdataKey key = new WdataKey();
		testQuery ppl = new testQuery();
		int date = 20121005;
		String station = "DKRM8";
		int time = 1215;
		key.setDate(date);
		key.setStation(station);
		key.setTime(time);
		ArrayList<Wdata> list = ppl.averageTemperature(key);
		Assert.assertNotNull(list);
		System.out.println("\nfound results" + list.size());
		
	}
	
	@Test
	@Ignore
	public void join() {
		testQuery ppl = new testQuery();
		int date = 20121005;
		String station = "DKRM8";
		int time = 1215;
		
		Wdata template = new Wdata();
		
		template.setStation(station);
		template.setTime(time);
		template.setDate(date);
		List<Wdata> list = ppl.find(template);
		Assert.assertNotNull("List NULL!! :(", list);
		System.out.println("\nfound " + list.size() + " results");
		list.get(0).getDate();
		for (Wdata p : list)
			System.out.println(p);
	}
	
	@Test
	//@Ignore
	public void averageTemperatureStates() {
		testQuery ppl = new testQuery();
		int date = 20121005;
		String station = "DKRM8";
		int tot = 0, count = 0;
		float avg = 0;
		Station template = new Station();
		template.setId(station);
		List<Station> list = ppl.findStation(template);
		Assert.assertNotNull("List NULL!! :(", list);
		System.out.println("\nfound " + list.size() + " results");
		for (Station p : list) {
			Collection<Wdata> i = p.getWdatas();
			Iterator<Wdata> o = i.iterator();
			while (o.hasNext()) {
				Wdata f = o.next();
				if (f.getDate() == date) {
					System.out.println("----> " + f.getTmpf() );
					count++;
					tot += f.getTmpf();
				}
			}
			avg = tot/count;
			System.out.println("---# > AVERAGE FOR STATION: " + p.getName() + "   ON DATE: " + date + " IS: " + avg);
		}
		
		
	}
	
	@Test
	public void averageTemperatures() {
		testQuery ppl = new testQuery();
		int date = 20121003;
		List<Station> list = ppl.queryfour(date);
		Assert.assertNotNull("List NULL!! :(", list);
		System.out.println("\nfound " + list.size() + " results" + list);
		for (Station p : list) {
			int tot = 0, count = 0;
			float avg = 0;
			Collection<Wdata> l = p.getWdatas();
			Iterator<Wdata> i = l.iterator();
			while (i.hasNext()) {
				Wdata o = i.next();
				count++;
				tot += o.getTmpf();
			}
			avg = tot/count;
			System.out.println("---# > AVERAGE FOR STATE: " + p.getState() + "   ON DATE: " + date + " IS: " + avg);
		}
	}
	
	@Test
	public void countStationsNotUS() {
		testQuery ppl = new testQuery();
		List<Station> list = ppl.countStation();
		Assert.assertNotNull("List NULL!! :(", list);
		System.out.println("\nfound " + list.size() + " results");
	}
	
	@Test
	public void minimumTemperatures() {
		testQuery ppl = new testQuery();
		int date = 20121003;
		List<Station> list = ppl.queryfour(date);
		Assert.assertNotNull("List NULL!! :(", list);
		System.out.println("\nfound " + list.size() + " results" + list);
		for (Station p : list) {
			float count = 0;
			Collection<Wdata> l = p.getWdatas();
			Iterator<Wdata> i = l.iterator();
			while (i.hasNext()) {
				Wdata o = i.next();
				count = o.getTmpf();
				if(o.getTmpf() < count && o.getTmpf() > -99) {
					count = o.getTmpf();
				}
			}
			System.out.println("---# > MINIMUM FOR STATE: " + p.getState() + "   ON DATE: " + date + " IS: " + count);
		}
	}
}
