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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@RooIntegrationTest(entity = Vet.class)
public class VetIntegrationTest {

    @Test
    public void testMarkerMethod() {
    }

	@Autowired
    VetDataOnDemand dod;

	@Test
    public void testCountVets() {
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", dod.getRandomVet());
        long count = Vet.countVets();
        Assert.assertTrue("Counter for 'Vet' incorrectly reported there were no entries", count > 0);
    }

	@Test
    public void testFindVet() {
        Vet obj = dod.getRandomVet();
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Vet' failed to provide an identifier", id);
        obj = Vet.findVet(id);
        Assert.assertNotNull("Find method for 'Vet' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Vet' returned the incorrect identifier", id, obj.getId());
    }

	@Test
    public void testFindAllVets() {
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", dod.getRandomVet());
        long count = Vet.countVets();
        Assert.assertTrue("Too expensive to perform a find all test for 'Vet', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Vet> result = Vet.findAllVets();
        Assert.assertNotNull("Find all method for 'Vet' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Vet' failed to return any data", result.size() > 0);
    }

	@Test
    public void testFindVetEntries() {
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", dod.getRandomVet());
        long count = Vet.countVets();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Vet> result = Vet.findVetEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Vet' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Vet' returned an incorrect number of entries", count, result.size());
    }

	@Test
    public void testFlush() {
        Vet obj = dod.getRandomVet();
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Vet' failed to provide an identifier", id);
        obj = Vet.findVet(id);
        Assert.assertNotNull("Find method for 'Vet' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyVet(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Vet' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testMergeUpdate() {
        Vet obj = dod.getRandomVet();
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Vet' failed to provide an identifier", id);
        obj = Vet.findVet(id);
        boolean modified =  dod.modifyVet(obj);
        Integer currentVersion = obj.getVersion();
        Vet merged = (Vet)obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Vet' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testPersist() {
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", dod.getRandomVet());
        Vet obj = dod.getNewTransientVet(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Vet' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Vet' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Vet' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testRemove() {
        Vet obj = dod.getRandomVet();
        Assert.assertNotNull("Data on demand for 'Vet' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Vet' failed to provide an identifier", id);
        obj = Vet.findVet(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Vet' with identifier '" + id + "'", Vet.findVet(id));
    }
}
