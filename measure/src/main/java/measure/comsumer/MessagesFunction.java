package measure.comsumer;

import java.util.Iterator;
import java.util.Set;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;

import measure.comsumer.datamodel.Result;
import measure.message.Message;
import scala.Tuple2;

public class MessagesFunction
		implements Function<Iterable<Message>, Set<Result>>, FlatMapFunction<Iterator<Tuple2<String, Message>>, Void> {

	private static final long serialVersionUID = 1L;

	public Set<Result> call(Iterable<Message> messages) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Void> call(Iterator<Tuple2<String, Message>> messagesKs_Vs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
