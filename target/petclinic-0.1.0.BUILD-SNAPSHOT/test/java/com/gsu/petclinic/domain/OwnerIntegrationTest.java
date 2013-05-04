package com.gsu.petclinic.domain;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.test.RooIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@RooIntegrationTest(entity = Owner.class)
public class OwnerIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    OwnerDataOnDemand dod;

	@Test
    public void testCountOwners() {
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", dod.getRandomOwner());
        long count = Owner.countOwners();
        Assert.assertTrue("Counter for 'Owner' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindOwner() {
        Owner obj = dod.getRandomOwner();
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Owner' failed to provide an identifier", id);
        obj = Owner.findOwner(id);
        Assert.assertNotNull("Find method for 'Owner' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Owner' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllOwners() {
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", dod.getRandomOwner());
        long count = Owner.countOwners();
        Assert.assertTrue("Too expensive to perform a find all test for 'Owner', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Owner> result = Owner.findAllOwners();
        Assert.assertNotNull("Find all method for 'Owner' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Owner' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindOwnerEntries() {
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", dod.getRandomOwner());
        long count = Owner.countOwners();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Owner> result = Owner.findOwnerEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Owner' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Owner' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Owner obj = dod.getRandomOwner();
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Owner' failed to provide an identifier", id);
        obj = Owner.findOwner(id);
        Assert.assertNotNull("Find method for 'Owner' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyOwner(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Owner' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Owner obj = dod.getRandomOwner();
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Owner' failed to provide an identifier", id);
        obj = Owner.findOwner(id);
        boolean modified =  dod.modifyOwner(obj);
        Integer currentVersion = obj.getVersion();
        Owner merged = (Owner)obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Owner' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", dod.getRandomOwner());
        Owner obj = dod.getNewTransientOwner(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Owner' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Owner' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Owner' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Owner obj = dod.getRandomOwner();
        Assert.assertNotNull("Data on demand for 'Owner' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Owner' failed to provide an identifier", id);
        obj = Owner.findOwner(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Owner' with identifier '" + id + "'", Owner.findOwner(id));
    }
}
