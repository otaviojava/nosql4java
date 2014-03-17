package linguagil.cassandra.tweet.repository;

import java.util.List;
import java.util.UUID;

import linguagil.cassandra.tweet.model.Tweet;

import org.easycassandra.persistence.cassandra.spring.CassandraRepository;
import org.easycassandra.persistence.cassandra.spring.CassandraTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("tweetRepository")
public class TweetRepository extends CassandraRepository<Tweet, UUID>{

	
	
	@Value(value="#{cassandraFactory.template}")
	private CassandraTemplate cassandraTemplate;
	
	@Override
	protected CassandraTemplate getCassandraTemplate() {
		
		return cassandraTemplate;
	}

	public List<Tweet> findByIndex(String nickName) {

		return cassandraTemplate.findByIndex("nickName", nickName, Tweet.class);
	}

	
}
