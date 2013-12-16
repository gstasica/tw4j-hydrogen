package com.tw4j.hydrogen.auth;

import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class OAuth {

  public static Authentication authentication() {
    String consumerKey = "od2uDdFe9Lg6bseRVrA"; 
    String consumerSecret = "IeJcRM4CxOloIGve3E5C4KsR6FBlDhXWtqM5v8";
    
    String token = "186101-LiaVPx1MW6VB0wGDbDEaCjANlpKQl8lpqcume5lll";
    String secret = "9YH9JbMMfbLursezjHqj9dzzqmffsg52bzYU9Dj6LNw";
    

    return new OAuth1(consumerKey, consumerSecret, token, secret);    
  }
}
