package measure;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerTest {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers", "172.18.200.135:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 10);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 1000; i++) {
			producer.send(new ProducerRecord<String, String>("hellokafka", Integer.toString(i), Integer.toString(i)),
					new Callback() {

						public void onCompletion(RecordMetadata metadata, Exception exception) {
							// TODO Auto-generated method stub
							System.out.println(metadata.partition());
						}
					});
		}

		producer.close();

	}

}
