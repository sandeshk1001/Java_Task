package Day15.tweet_assignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class APIExample {
    private Map<String, User> userProfileMap = new HashMap<>();
    private Map<String, List<Tweet>> tweetsMap = new HashMap<>();

    @PostMapping("tweet/createuser")
    private ResponseEntity<String> createUser(@RequestBody Map<String,String> requestbody){
        ResponseEntity<String> responseEntity=null;
        String name=requestbody.get("name");
        String email=requestbody.get("email");
        String password=requestbody.get("password");
        if (containsInvalidChar(name))
            responseEntity=new ResponseEntity<>("name contains invalid character",HttpStatus.BAD_REQUEST);
        else if (containsInvalidEmail(email)){
            responseEntity=new ResponseEntity<>("Email contains invalid character or alerady present",HttpStatus.BAD_REQUEST);
        }else if(containsInvalidPassword(password)){
            
        }else {
            User user =new User(name,email,password);
            userProfileMap.put(email,user);
            System.out.println(userProfileMap);
            responseEntity=new ResponseEntity<>("Registered successfully"+ userProfileMap,HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping("tweet/posttweet")
    private ResponseEntity<String> postTweet(@RequestBody Tweet tweet){
        ResponseEntity<String> responseEntity =null;
        String name=tweet.getName();
        String email=tweet.getEmail();
        String t = tweet.getTweet();
        if (containsInvalidChar(name)){
            responseEntity=new ResponseEntity<>("name contains invalid",HttpStatus.BAD_REQUEST);
        }else if (!isContainsEmail(email)){
            responseEntity=new ResponseEntity<>("Email contains invalid character or not present",HttpStatus.BAD_REQUEST);
        }else {
            if (tweetsMap.containsKey(email)){
                tweetsMap.get(email).add(tweet);
                System.out.println("tweet map 2:"+tweetsMap);
                responseEntity=new ResponseEntity<>("Tweet added",HttpStatus.OK);
            }else{
                List<Tweet> tweetList=new ArrayList<>();
                tweetList.add(tweet);
                tweetsMap.put(email, tweetList);
                System.out.println("tweet map 1 :"+tweetsMap);
                responseEntity=new ResponseEntity<>("Tweet added",HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    @GetMapping("tweet/alltweets")
    private ResponseEntity<String> allTweets(@RequestParam String email,@RequestParam String password){
        ResponseEntity<String> responseEntity =null;
        if (!isContainsEmail(email)){
            responseEntity=new ResponseEntity<>("Email not present",HttpStatus.BAD_REQUEST);
        }else if(containsInvalidPassword(password)){
            responseEntity=new ResponseEntity<>("Password contains invalid character",HttpStatus.BAD_REQUEST);
        } else{
            User user =userProfileMap.get(email);
            if (user.getPassword().equals(password)){
                System.out.println("Tweets are :"+tweetsMap.get(email));
                responseEntity=new ResponseEntity<>("Tweets are :\n"+tweetsMap.get(email)+"\n",HttpStatus.OK);
            }else {
                responseEntity=new ResponseEntity<>("Pasword not match",HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return responseEntity;
    }

    private boolean containsInvalidPassword(String password) {
        if (password==null)
            return true;
        return false;
    }

    private boolean containsInvalidChar(String name) {
        if (name == null) {
            return true;
        }else if(name.length()>5){
            return false;
        }
        return true;
    }

    private boolean containsInvalidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        if(email==null){
            return true;
        }else if(m.matches()){
            if (isContainsEmail(email)){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    private Boolean isContainsEmail(String email) {
        if (userProfileMap.containsKey(email))
            return true;
        return false;
    }

}
