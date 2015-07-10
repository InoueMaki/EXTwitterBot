//じぶんのタイムラインを表示する

package sample;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TweetTest {
	
	private static final String consumerKey = "";
	private static final String consumerSecret = "";
	private static final String accessToken = "";
	private static final String accessTokenSecret = "";
	
	public static void main(String[] args){
		TweetTest test = new TweetTest();
		test.startUserStream();
	}
	
	// User Stream APIを開始する
	private void startUserStream()
	{
		ConfigurationBuilder builder = new ConfigurationBuilder();
		
		builder.setOAuthConsumerKey(consumerKey);
		builder.setOAuthConsumerSecret(consumerSecret);
		builder.setOAuthAccessToken(accessToken);
		builder.setOAuthAccessTokenSecret(accessTokenSecret);
		builder.setUserStreamBaseURL( "https://userstream.twitter.com/2/" );
		Configuration conf = builder.build();

		TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory(conf);
		TwitterStream twitterStream = twitterStreamFactory.getInstance();
		twitterStream.addListener(new MyStreamAdapter());
		twitterStream.user();
	}
	// イベントを受け取るリスナーオブジェクト
	class MyStreamAdapter extends UserStreamAdapter
	{
		public void onStatus(Status status) {
			super.onStatus(status);
			System.out.println("@" + status.getUser().getScreenName() + " : " + status.getText());
		}
	}
}