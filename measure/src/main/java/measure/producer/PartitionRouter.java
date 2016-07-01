package measure.producer;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class PartitionRouter implements Partitioner {

	private static final Log LOG = LogFactory.getLog(PartitionRouter.class);

	public void configure(Map<String, ?> configs) {
		// 今天你飞哥不想写这个代码，没啥用。
	}

	public void close() {

	}

	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		List<PartitionInfo> partitions = cluster.availablePartitionsForTopic(topic);
		LOG.debug(String.format("Topic %s has count %s patitions", topic, partitions.size()));
		return (keyBytes.hashCode() % partitions.size());
	}

}
