package measure.comsumer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import measure.message.Message;

public class SparkConsumer {

	public static final String APPNAME = "MEASURE";
	private static final Log LOG = LogFactory.getLog(SparkConsumer.class);
	private Configuration configuration = null;

	public SparkConsumer(Configuration configuration) {
		this.configuration = configuration;
	}

	private JavaStreamingContext createSparkStreamingContext() {
		String master = configuration.getString("spark", "spark://localhost:7077");
		String[] jars = configuration.getStringArray("jars");
		SparkConf conf = new SparkConf().setAppName(APPNAME).setMaster(master).setJars(jars);
		Duration batchDuration = new Duration(configuration.getInt("spark.stream.batchDuration", 500));
		JavaStreamingContext context = new JavaStreamingContext(conf, batchDuration);
		return context;
	}

	public JavaPairReceiverInputDStream<String, Message> getDStream() {
		JavaStreamingContext context = createSparkStreamingContext();
		LOG.debug("Fuck I don`t know wich API is better");
		// KafkaUtils.createDirectStream(context, arg1, arg2, arg3, arg4, arg5,
		// arg6, arg7, arg8)
		return null;
	}

}
