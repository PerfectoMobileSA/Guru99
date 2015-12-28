import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WindTunnelUtils {
	
	private static final String WIND_TUNNEL_LOCATION_CAPABILITY = "windTunnelLocation";
	private static final String WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY = "windTunnelLocationAddress";
	private static final String WIND_TUNNEL_ORIENTATION_CAPABILITY = "windTunnelOrientation";
	private static final String WIND_TUNNEL_VNETWORK_CAPABILITY = "windTunnelVNetwork";
	private static final String WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY = "windTunnelBackgroundRunningApps";

	private static final String IMAGE = "image";
	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";
	private static final String PROPERTIES = "properties";
	private static final String SETTINGS = "settings";

	/**
	 * Example:
	 * String repositoryKey = uploadWindTunnelPersona(host, user, password, "Pedro", "This is Pedro's profile", "PUBLIC:personas\\Perdo.jpg", null, "Boston", 
	 * "landscape", "4G LTE Advanced Good", "Waze,YouTube", "PRIVATE:Personas");
	 * @param host
	 * @param user
	 * @param password 
	 * @param name persona name
	 * @param description persona description
	 * @param image persona image as repository item
	 * @param location location as coordinates with format latitude,longitude
	 * @param locationAddress location as address
	 * @param orientation device orientation (landscape or portrait)
	 * @param vnetworkProfile virtual network profile
	 * @param applications list of application names
	 * @return persona repository key
	 */
	public static String uploadWindTunnelPersona(String host, String user, String password, String name, String description, String image, String location, 
			String locationAddress, String orientation, String vnetworkProfile, String applications, String repositoryFolder) 
					throws UnsupportedEncodingException, MalformedURLException, IOException {
		if (repositoryFolder == null) {
			throw new RuntimeException("Can't upload persona without repository folder");
		}
		String persona = createWindTunnelPersona(name, description, image, location, locationAddress, orientation, vnetworkProfile, applications);
		String repositoryKey = repositoryFolder + "/" + name + ".json"; 
		RemoteWebDriverUtils.uploadMedia(host, user, password, persona.getBytes(), repositoryKey);
		return repositoryKey;
	}

	/**
	 * Example:
	 * String persona = createWindTunnelPersona("Pedro", "This is Pedro's profile", "PUBLIC:personas\\Perdo.jpg", null, "Boston", 
	 * "landscape", "4G LTE Advanced Good", "Waze,YouTube");
	 * @param name persona name
	 * @param description persona description
	 * @param image persona image as repository item
	 * @param location location as coordinates with format latitude,longitude
	 * @param locationAddress location as address
	 * @param orientation device orientation (landscape or portrait)
	 * @param vnetworkProfile virtual network profile
	 * @param applications list of application names
	 * @return persona in json format
	 */
	public static String createWindTunnelPersona(String name, String description, String image, String location, String locationAddress, String orientation, 
			String vnetworkProfile, String applications) throws JsonProcessingException {
		if (name == null) {
			throw new RuntimeException("Can't create persona without name");
		}

		Map<String, Object> persona = new HashMap<>();
		Map<String, Object> properties = createProperties(name, description, image);
		persona.put(PROPERTIES, properties);
		Map<String, Object> settings = createSettings(location, locationAddress, orientation, vnetworkProfile, applications);
		persona.put(SETTINGS, settings);
		String json = convertToJson(persona);
		return json;
	}
	
	/**
	 * Example: String settings = createWindTunnelSettings(null, "Boston", "landscape", "4G LTE Advanced Good", "Waze,YouTube");
	 * @param location location as coordinates with format latitude,longitude
	 * @param locationAddress location as address
	 * @param orientation device orientation (landscape or portrait)
	 * @param vnetworkProfile virtual network profile
	 * @param applications list of application names
	 * @return settings in json format
	 * @throws JsonProcessingException
	 */
	public static String createWindTunnelSettings(String location, String locationAddress, String orientation, String vnetworkProfile, String applications) 
			throws JsonProcessingException {
		Map<String, Object> settings = createSettings(location, locationAddress, orientation, vnetworkProfile, applications);
		String json = convertToJson(settings);
		return json;
	}
	
	private static Map<String, Object> createProperties(String name, String description, String image) {
		Map<String, Object> properties =  new HashMap<>();
		if (name != null) {
			properties.put(NAME, name);
		}
		if (description != null) {
			properties.put(DESCRIPTION, description);
		}
		if (image != null) {
			properties.put(IMAGE, image);
		}
		return properties;
	}

	private static Map<String, Object> createSettings(String location, String locationAddress, String orientation, String vnetworkProfile, String applications) {
		Map<String, Object> settings =  new HashMap<>();
		if (location != null) {
			settings.put(WIND_TUNNEL_LOCATION_CAPABILITY, location);
		}
		if (locationAddress != null) {
			settings.put(WIND_TUNNEL_LOCATION_ADDRESS_CAPABILITY, locationAddress);
		}
		if (orientation != null) {
			settings.put(WIND_TUNNEL_ORIENTATION_CAPABILITY , orientation);
		}
		if (vnetworkProfile != null) {
			settings.put(WIND_TUNNEL_VNETWORK_CAPABILITY , vnetworkProfile);
		}
		if (applications != null) {
			settings.put(WIND_TUNNEL_BACKGROUND_RUNNING_APPS_CAPABILITY , applications);
		}
		return settings;
	}

	private static String convertToJson(Map<String, Object> content) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(content);
		return json;
	}
	
}
