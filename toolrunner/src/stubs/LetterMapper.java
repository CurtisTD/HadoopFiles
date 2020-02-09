package stubs;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	//Convert input value to usable string
    StringTokenizer wordList = new StringTokenizer(value.toString());
    
    while(wordList.hasMoreTokens()) {
    	//Write first letter of word and length of word
    	String word = wordList.nextToken();
    	context.write(new Text(word.substring(0, 1)), 
    			new IntWritable(word.length()));
    }

  }
}
