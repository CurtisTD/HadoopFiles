package stubs;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private boolean caseSensitive = false; //Determines if case sensitive
	public void setup(Context context){
		Configuration config = context.getConfiguration();
		caseSensitive = config.getBoolean("caseSensitive", false);
	}
	
	
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	//Convert input value to usable string
    StringTokenizer wordList = new StringTokenizer(value.toString());
    
    while(wordList.hasMoreTokens()) {
    	//Write first letter of word and length of word
    	String word = wordList.nextToken();
    	
    	//Case sensitive processing
    	if(word.length() > 0) {
    		if(caseSensitive == false){
    			word = word.toLowerCase(); //Converts word to lowercase
    		}
    		context.write(new Text(word.substring(0, 1)), 
        			new IntWritable(word.length()));
    	}
    	
    	
    }

  }
}
