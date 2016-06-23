package measure.producer;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.Future;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import measure.message.Message;

public class Postman {

	private Configuration configuration;
	private KafkaProducer<String, Message> producer;
	private static final Log LOG = LogFactory.getLog(Postman.class);
	private final Vector<String> topics = new Vector<String>();

	public Postman(Configuration configuration) {
		this(configuration, getKafkaConf(configuration));
	}

	public Postman(Configuration configuration, Properties properties) {
		List<Object> tpcs = configuration.getList("topics");
		for (Object t : tpcs) {
			topics.addElement((String) t);
			LOG.info("add topic " + (String) t);
		}
		this.setConfiguration(configuration);
		this.producer = new KafkaProducer<String, Message>(properties);

	}

	/*
	 * 此处发送消息，做消息编码处理
	 */
	public Future<RecordMetadata> send(ProducerRecord<String, Message> record) {
		return producer.send(record);
	}

	/*
	 * test method send
	 */
	public void send(Message message) {
		LOG.debug("send data to kafka");
		String key = message.getBuild();
		for (String topic : topics) {
			ProducerRecord<String, Message> record = new ProducerRecord<String, Message>(topic, key, message);
			LOG.debug("topic:" + topic + "  =====>" + message);
			this.producer.send(record);
		}

	}

	public void send(String message) {

	}

	/*
	 * 从configuration获得相关kafka producer配置信息
	 */
	public static Properties getKafkaConf(Configuration configuration) {
		Properties properties = new Properties();
		String producer_config_prefix = "measure.producer";
		Iterator<String> keys = configuration.getKeys(producer_config_prefix);
		String key = null;
		while (keys.hasNext()) {
			key = keys.next();
			properties.put(key.replaceFirst("measure.producer.", ""), configuration.getString(key));
		}
		return properties;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
