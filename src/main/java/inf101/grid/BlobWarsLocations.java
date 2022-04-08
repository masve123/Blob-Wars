package inf101.grid;

import java.util.Objects;

/**
 * This class represents locations on a grid.
 * That means indices for row and column.
 * <p>
 * The difference between this class and "Location" is that this class
 * holds two different locations, in comparison to a single one.
 */
public class BlobWarsLocations {


    private Location fromLocation;
    private Location toLocation;

    public BlobWarsLocations(Location fromLocation, Location toLocation) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }


    public Location getToLocation() {
        return this.toLocation;
    }

    public Location getFromLocation() {
        return this.fromLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BlobWarsLocations)) {
            return false;
        }
        BlobWarsLocations blobWarsLocations = (BlobWarsLocations) o;
        return Objects.equals(fromLocation, blobWarsLocations.fromLocation) && Objects.equals(toLocation, blobWarsLocations.toLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromLocation, toLocation);
    }


    @Override
    public String toString() {
        return "(" + fromLocation + "," + toLocation + ")";
    }

    
    
}
