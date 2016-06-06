package measure.producer;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.commons.configuration.Configuration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class Postman {

	private Configuration configuration;
	KafkaProducer<String, String> producer;

	public Postman(Configuration configuration) {
		this(configuration, getKafkaConf(configuration));
	}

	public Postman(Configuration configuration, Properties properties) {
		this.configuration = configuration;
		// this.producer = new KafkaProducer<String, String>(properties);
	}

	public Future<RecordMetadata> send(ProducerRecord<String, String> record) {
		return producer.send(record);
	}

	/*
	 * test method send
	 */
	public void send(String message) {
		System.out.println("PostMan get message : " + message);
	}

	/*
	 * 从configuration获得相关kafka producer配置信息
	 */
	public static Properties getKafkaConf(Configuration configuration) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				configuration.getString("bootstrap.servers", "default"));
		properties.put(ProducerConfig.ACKS_CONFIG, configuration.getString("acks", "default"));
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, configuration.getString("batch.size", "100"));
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, configuration.getString("buffer.memory", "100"));
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, configuration.getString("client.id", "1"));
		properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, configuration.getString("compression.type", "default"));
		properties.put(ProducerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG,
				configuration.getString("connections.max.idle.ms", "5000"));
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				configuration.getString("key.serializer", "default"));
		properties.put(ProducerConfig.LINGER_MS_CONFIG, configuration.getString("linger.ms", "100"));
		properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, configuration.getString("max.block.ms", "200"));
		properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
				configuration.getString("max.in.flight.requests.per.connection", "default"));
		properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, configuration.getString("max.request.size", "200"));
		properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, configuration.getString("metadata.max.age.ms", "200"));
		properties.put(ProducerConfig.METRIC_REPORTER_CLASSES_CONFIG,
				configuration.getString("metric.reporters", "default"));
		properties.put(ProducerConfig.METRICS_NUM_SAMPLES_CONFIG,
				configuration.getString("metrics.num.samples", "default"));
		properties.put(ProducerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG,
				configuration.getString("metrics.sample.window.ms", "200"));
		properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
				configuration.getString("partitioner.class", "default"));
		properties.put(ProducerConfig.RECEIVE_BUFFER_CONFIG, configuration.getString("receive.buffer.bytes", "200"));
		properties.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG,
				configuration.getString("reconnect.backoff.ms", "200"));
		properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, configuration.getString("request.timeout.ms", "200"));
		properties.put(ProducerConfig.RETRIES_CONFIG, configuration.getString("retries", "3"));
		properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, configuration.getString("retry.backoff.ms", "200"));
		properties.put(ProducerConfig.SEND_BUFFER_CONFIG, configuration.getString("send.buffer.bytes", "200"));
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				configuration.getString("value.serializer", "default"));
		return properties;
	}

}
