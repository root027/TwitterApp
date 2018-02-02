# TwitterApp
This is a web application which allows a user to input a twitter handle and obtain the tweets of followers which are most 
similar to the twitter handle. The details returned include profile image of friend and measure of closeness of tweets. 
Twitter friends are stored in HBase, the app also uses MySQL for persisting. API calls to twitter are REST based. 
It also analyzes the tweets of the twitter handle to display the result of the sentiment analysis.
