package measure.comsumer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import kafka.serializer.StringDecoder;
import measure.message.Message;

public class SparkConsumer {

	public static final String APPNAME = "MEASURE";
	private static final Log LOG = LogFactory.getLog(SparkConsumer.class);
	private Configuration configuration = null;
	private JavaStreamingContext context;

	public SparkConsumer(Configuration configuration) {
		this.configuration = configuration;
		this.context = createSparkStreamingContext(configuration);
	}

	private JavaStreamingContext createSparkStreamingContext(Configuration configuration) {
		String master = configuration.getString("spark", "spark://localhost:7077");
		LOG.info("Spark master : " + master);
		String[] jars = configuration.getStringArray("jars");
		SparkConf conf = new SparkConf().setAppName(APPNAME).setMaster(master).setJars(jars);
		Duration batchDuration = new Duration(configuration.getInt("spark.stream.batchDuration", 500));
		JavaStreamingContext context = new JavaStreamingContext(conf, batchDuration);
		return context;
	}

	public void runDStream() {
		LOG.debug("Fuck I don`t know wich API is better and I don`t know how to decode KEY and VALUE ");
		JavaPairInputDStream<String, Message> dstream = KafkaUtils.createDirectStream(context, String.class,
				Message.class, StringDecoder.class, MessageDecoder.class, getConsumerConfig(), getTopics());
		executor(dstream);

	}

	private void executor(JavaPairInputDStream<String, Message> dstream) {
		LOG.info("目前尚未实现");
		dstream.print(10);
		//dstream.groupByKey().mapValues(new MessagesFuction());
		//dstream.mapPartitions(new MessagesFuction());

	}

	private Map<String, String> getConsumerConfig() {
		Map<String, String> config = new HashMap<String, String>();
		String producer_config_prefix = "measure.consumer";
		Iterator<String> keys = configuration.getKeys(producer_config_prefix);
		String key = null;
		while (keys.hasNext()) {
			key = keys.next();
			config.put(key.replaceFirst("measure.consumer.", ""), configuration.getString(key));
		}
		return config;
	}

	private Set<String> getTopics() {
		Set<String> topics = new HashSet<String>();
		for (String topic : configuration.getStringArray("topics")) {
			topics.add(topic);
		}
		return topics;
	}

	public void run() {
		LOG.info("CONSUMER SYSTEM RUN");
		runDStream();
		this.context.start();
		this.context.awaitTerminationOrTimeout(1000);
	}

	public void stop() {
		LOG.info("STOP CONSUMER SYSTEM");
		this.context.stop(true, true);
	}
}
