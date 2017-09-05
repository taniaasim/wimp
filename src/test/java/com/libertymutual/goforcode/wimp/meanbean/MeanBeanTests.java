package com.libertymutual.goforcode.wimp.meanbean;

import org.junit.Test;
import org.meanbean.test.BeanTester;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;

public class MeanBeanTests {

	@Test
	public void testActor() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Actor.class);
	}
	
	@Test
	public void testMovie() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Movie.class);
	}
	
	@Test
	public void testAward() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Award.class);
	}

}
