package stubs;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

public class IndexMapper extends Mapper<Text, Text, Text, Text> {

  @Override
  public void map(Text key, Text value, Context context) throws IOException,
      InterruptedException {

    /*
     * TODO implement
     */
	  FileSplit fileSplit = (FileSplit) context.getInputSplit();
	  Path path = fileSplit.getPath();
	  String fileName = path.getName();
	  
	  String line = value.toString();
	  StringBuffer str = new StringBuffer();
	  str.append(fileName);
	  str.append('@');
	  str.append(key);
	  
	  Text nextV = new Text(str.toString());
	  for(String word : line.split("\\W+")){
		  if(word.length() >= 1){
			  context.write(new Text(word), nextV);
		  }
	  }
    
  }
}