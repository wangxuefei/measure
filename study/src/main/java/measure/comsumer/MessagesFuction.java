package measure.comsumer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.FlatMapFunction2;
import org.apache.spark.api.java.function.Function;

import measure.comsumer.datamodel.Result;
import measure.message.Message;
import scala.Tuple2;

public class MessagesFuction implements Function<Iterable<Message>, Set<Result>> ,FlatMapFunction<Iterator<Tuple2<String,Message>>, Void> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Set<Result> call(Iterable<Message> messages) throws Exception {
		Set<Result> results = new HashSet<Result>();
		Iterator<Message> messageIterator = messages.iterator();
		while (messageIterator.hasNext()){
			 Message message = messageIterator.next();
			 
		}
		
		return results;
	}

	public Iterable<Void> call(Iterator<Tuple2<String, Message>> arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
