package mo.bitcode.voyager.service.geo_location;

import mo.bitcode.voyager.common.model.GeoLocationAttributes;

import java.util.List;

public interface GeoLocationProximityService {

  long getDistance(GeoLocationAttributes location1, GeoLocationAttributes location2);
  <T extends GeoLocationAttributes> List<T> getNearBy(T current, List<T> candidates, long withIn);

}
