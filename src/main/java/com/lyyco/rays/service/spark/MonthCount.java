package com.lyyco.rays.service.spark;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/*
 * 两个大表
 * 通过笛卡尔积实现 reduce join
 * 适用场景：两个表的连接字段key都不唯一(包含一对多，多对多的关系)
 */
public class MonthCount {
	/**
	 * 为来自不同表(文件)的key/value对打标签以区别不同来源的记录。
	 * 然后用连接字段作为key，其余部分和新加的标志作为value，最后进行输出。
	 */
	public static class ReduceJoinByCartesianProductMapper extends Mapper<Object,Text,Text, JoinBean>{
        
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			// 创建时间
            String cREATE_DT = "";
			// 交易金额
            String aMOUNT_IN = "";
            
            String pathName=((FileSplit)context.getInputSplit()).getPath().toString();
//            
            if(pathName.endsWith("PAY_PAYMENT_201802281551.csv")){
                String[] valueItems=value.toString().split(",");
				//过滤掉脏数据
                try {
                	if( "2017".equals(valueItems[20].substring(0, 4)) && "SUCCESS".equals(valueItems[15])){
                		cREATE_DT=valueItems[20].substring(0, 7);
                        if(valueItems[10].isEmpty()){
                        	valueItems[10]="0";
                        }
                        aMOUNT_IN=valueItems[10];
                        JoinBean bean = new JoinBean(cREATE_DT,aMOUNT_IN);
                        context.write(new Text(cREATE_DT),bean);
                	}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }
    }
	
	public static class ReduceJoinByCartesianProductReducer extends Reducer<Text,JoinBean,NullWritable,JoinBean> {
		   List<JoinBean> list=new ArrayList<JoinBean>();
		public void reduce(Text key, Iterable<JoinBean> values,
	                       Context context  ) throws IOException, InterruptedException {
	      BigDecimal sum = BigDecimal.ZERO;
	      for (JoinBean val : values) {
//	    	  使用joinbean获取到金额的值然后累加。
	        sum  = sum.add(new BigDecimal(val.getaMOUNT_IN().toString()));
	      }
//	    将得到的金额的总和加入到list集合当中。
//	     一个数据类型转换,转换成joinbean这个对象。将整个key 和对应的累加和全部加入到list集合当中去。
	      list.add(new JoinBean(key.toString(),sum.toPlainString()));
	      
	    }
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		Collections.sort(list, new Comparator<JoinBean>() {
			@Override
			//			然后对joinbean当中金额的数值进行比较，然后
			public int compare(JoinBean o1, JoinBean o2) {
				return new Double(o2.getaMOUNT_IN()).compareTo(new Double(o1.getaMOUNT_IN()));
			}
		  });
		 for (JoinBean jb : list) {
			context.write(NullWritable.get(), jb);
		}
	}
	
	}
	/**
	 * 用于传输数据
	 */
	public static class JoinBean implements Writable {
		 String cREATE_DT = "";
		// 交易金额
	     String aMOUNT_IN = "";
	    
	    public JoinBean() {
	    	
	    }
	    
		public JoinBean(String cREATE_DT, String aMOUNT_IN) {
			super();
			this.cREATE_DT = cREATE_DT;
			this.aMOUNT_IN = aMOUNT_IN;
		}
		@Override
		public String toString() {
			return this.cREATE_DT + "," + this.aMOUNT_IN ;
		}
		
		

		public String getcREATE_DT() {
			return cREATE_DT;
		}

		public void setcREATE_DT(String cREATE_DT) {
			this.cREATE_DT = cREATE_DT;
		}

		public String getaMOUNT_IN() {
			return aMOUNT_IN;
		}

		public void setaMOUNT_IN(String aMOUNT_IN) {
			this.aMOUNT_IN = aMOUNT_IN;
		}

		@Override
		public void write(DataOutput out) throws IOException {
			out.writeUTF(this.cREATE_DT);
			out.writeUTF(this.aMOUNT_IN);
		}

		@Override
		public void readFields(DataInput in) throws IOException {
			this.cREATE_DT = in.readUTF();
			this.aMOUNT_IN = in.readUTF();
		}
	}
	          
	 public static void main(String[] args) throws Exception {
		 // 设置目录
			args = new String[] {
				"D:\\test",
				"D:\\test\\output"
			};
			
			Configuration conf = new Configuration();

		 // 如果目标文件夹存在，则删除
	        Path mypath = new Path(args[1]);
	        FileSystem hdfs = mypath.getFileSystem(conf);
	        if (hdfs.isDirectory(mypath)) {
	            hdfs.delete(mypath, true);
	        }
			Job job = Job.getInstance(conf, "ReduceJoin2");
			job.setJarByClass(MonthCount.class);
			job.setMapperClass(ReduceJoinByCartesianProductMapper.class);
			job.setReducerClass(ReduceJoinByCartesianProductReducer.class);
			// reduce�ĸ�������Ϊ1
	        job.setNumReduceTasks(1);
	        
			job.setMapOutputKeyClass(Text.class);
	        job.setMapOutputValueClass(JoinBean.class);
			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(JoinBean.class);

			// specify input and output DIRECTORIES (not files)
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			

			if (!job.waitForCompletion(true)) {
				return;
			}
		}
	 }



