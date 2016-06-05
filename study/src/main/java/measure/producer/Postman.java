package measure.producer;


import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.commons.configuration.Configuration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class Postman {
	
	private Configuration configuration ;
	
	KafkaProducer<String, String> producer;
	
	public Postman(Configuration configuration){
		this(configuration,new Properties());
	}
	
	public Postman(Configuration configuration , Properties properties){
		Iterator<String> kafka_producer_confs = configuration.getKeys("measure.producer");
		String tempKey = null;
		while(kafka_producer_confs.hasNext()){
			tempKey = kafka_producer_confs.next().replaceFirst("measure.producer", "").trim();
			properties.put(tempKey, configuration.getString(tempKey));
		}
		this.configuration = configuration;
		this.producer  = new KafkaProducer<String, String>(properties);
	}

	public Future<RecordMetadata> send(ProducerRecord<String, String> record) {
       return producer.send(record);
    }
	
	
	
	
	
}
