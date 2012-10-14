package drsy.weather.app;

import java.util.ArrayList;
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
}
