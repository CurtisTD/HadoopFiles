package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    /*
     * TODO implement
     */
	  String line = value.toString();
	  StringBuffer str = new StringBuffer();
	  boolean flag = true;
	  for(String word : line.split("\\W+")){
		  if(word.length() >= 1){
			  if(flag) { //When flag is true
				  str.append(word.toLowerCase());
				  flag = false;
				  continue;
			  }
			  //When flag is false
			  str.append(';');
			  str.append(word.toLowerCase());
			  context.write(new Text(str.toString()), new IntWritable(1));
			  str.delete(0, str.length());
			  str.append(word.toLowerCase());
		  }
	  }
    
  }
}
