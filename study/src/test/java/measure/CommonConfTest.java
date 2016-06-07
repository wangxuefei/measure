package measure;

import java.util.Iterator;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CommonConfTest {

	public static void main(String[] args) throws ConfigurationException {
		// TODO Auto-generated method stub
		CompositeConfiguration compositeConfiguration = new CompositeConfiguration();

		Configuration config = new PropertiesConfiguration("property");

		compositeConfiguration.addConfiguration(config);

		Iterator<String> keys = compositeConfiguration.getKeys();
		// System.err.println(keys.next());
		// System.err.println(keys.next());
		// System.err.println(keys.next());
		// System.err.println(keys.next());
		// System.err.println(keys.next());

		Iterator<String> ks = compositeConfiguration.getKeys("measure.producer");

		System.out.println(ks.next().replaceFirst("measure.producer.", ""));
		System.out.println(ks.next().replaceFirst("measure.producer.", ""));
		System.out.println(ks.next().replaceFirst("measure.producer.", ""));
		// Properties ps = compositeConfiguration.getProperties("key1");

		// Set<Object> kesy = ps.keySet();
		// for( Object k:kesy){
		// System.out.println(k);
		// }
	}

}
