package geoPoliticalZoning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeoPoliticalZoneFinderTest {
    private GeoPoliticalZoneFinder zoneFinder;

    public GeoPoliticalZoneFinderTest() {
    }

    @BeforeEach
    public void setUp() {
        this.zoneFinder = new GeoPoliticalZoneFinder();
    }

    @Test
    public void findNorthCentralZoneTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("FCT");
        assertEquals(GeoPoliticalZone.NORTH_CENTRAL, zone);
    }

    @Test
    public void findNorthEastZoneTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("Adamawa");
        assertEquals(GeoPoliticalZone.NORTH_EAST, zone);
    }

    @Test
    public void findNorthWestZoneTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("Kaduna");
        assertEquals(GeoPoliticalZone.NORTH_WEST, zone);
    }

    @Test
    public void findSouthEastZoneTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("Anambra");
        assertEquals(GeoPoliticalZone.SOUTH_EAST, zone);
    }

    @Test
    public void findSouthSouthZoneTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("Rivers");
        assertEquals(GeoPoliticalZone.SOUTH_SOUTH, zone);
    }

    @Test
    public void findSouthWestZoneTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("Lagos");
        assertEquals(GeoPoliticalZone.SOUTH_WEST, zone);
    }

    @Test
    public void findZoneWithLowerCaseStateNameTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("lagos");
        assertEquals(GeoPoliticalZone.SOUTH_WEST, zone);
    }

    @Test
    public void findZoneReturnsNullForInvalidStateTest() {
        GeoPoliticalZone zone = this.zoneFinder.findZone("Dubia");
        assertNull(zone);
    }
}
