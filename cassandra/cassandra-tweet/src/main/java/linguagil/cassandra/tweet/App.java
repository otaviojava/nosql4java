package linguagil.cassandra.tweet;

import java.util.Date;
import java.util.UUID;

import linguagil.cassandra.tweet.model.Tweet;
import linguagil.cassandra.tweet.repository.TweetRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	@SuppressWarnings("resource")
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
    	
    	TweetRepository personService=ctx.getBean(TweetRepository.class);
    	
    	UUID uuid = UUID.randomUUID();
    	Tweet tweet = new Tweet();
    	tweet.setId(uuid);
    	tweet.setNickName("otaviojava");
    	tweet.setMensagem("Linguágil 2014 está bombando!");
    	tweet.setTime(new Date());
    	
    	personService.save(tweet);
    	tweet.setId(UUID.randomUUID());
    	tweet.setMensagem("Bruno e o Edson estarão no linguagil 2014");
    	personService.save(tweet);
    	
    	Tweet otavio=personService.findOne(uuid);
    	System.out.println(otavio);
    	
    	System.out.println(personService.findByIndex(tweet.getNickName()));
    	
    }
}
