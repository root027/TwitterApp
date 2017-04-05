package webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import webapp.model.Model;

public class ManageTweet extends Action {

	public ManageTweet(Model model) {
	}

	@Override
	public String getName() {
		return "managetweet.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String uname = (String) request.getSession().getAttribute("twitterid");
		ArrayList<String> tweetList = new ArrayList<String>();
		ArrayList<String> finalList = new ArrayList<String>();
		List<Status> tweets = new ArrayList<Status>();
		String CONSUMER_KEY = "u4Q4MzvhigGlpUXXFKqXgk1OA";
		String CONSUMER_SECRET = "XhMIfPWTZIxIctn0MLf0RnPv3fbNnsghBEp1LoXogTnmrKLgSK";
		String ACCESS_KEY = "149465857-9lTkGIItzNI3ttXSimR2g4j5KiUjts09tplN1frQ";
		String ACCESS_SECRET = "XPeJF9P1OF80Yk1iQzXmS1iQTaBTNGW5UJRrcpHMWn8y4";
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET)
				.setOAuthAccessToken(ACCESS_KEY).setOAuthAccessTokenSecret(ACCESS_SECRET);

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		try {
			tweets = twitter.getUserTimeline(uname);
		} catch (TwitterException te) {
			return "error.jsp";
		}
		int count = 0;
		for (Status tweet : tweets) {
			if (count >= 10)
				break;
			tweetList.add(tweet.getText());
			count++;
		}
		NLP.init();
		for (String tweet : tweetList) {
			finalList.add(tweet + " : " + NLP.findSentiment(tweet));
		}

		request.setAttribute("outputstr", finalList);
		return "user.jsp";
	}
}