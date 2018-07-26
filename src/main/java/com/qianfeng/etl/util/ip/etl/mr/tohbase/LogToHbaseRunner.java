package com.qianfeng.etl.util.ip.etl.mr.tohbase;

import com.qianfeng.common.EventLogsConstant;
import com.qianfeng.etl.util.ip.GlobalConstants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @Auther: lyd
 * @Date: 2018/7/26 11:49
 * @Description:驱动类
 */
public class LogToHbaseRunner implements Tool{
    private static final Logger logger = Logger.getLogger(LogToHbaseRunner.class);
    Configuration conf = null;

    @Override
    public void setConf(Configuration conf) {
        this.conf = HBaseConfiguration.create();
    }

    @Override
    public Configuration getConf() {
        return this.conf;
    }

    /**
     * yarn jar /home/gp1706.jar com.qianfeng.etl.mr.tohbase.ToLogRunner -d 2018-07-26
     * @param args
     * @return
     * @throws Exception
     */
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = this.getConf();
        //设置处理的参数
        this.setArgs(args,conf);
        //判断hbase的表是否存在
        this.HbaseTableExists(conf);
        //获取job
        Job job = Job.getInstance(conf,"to hbase etl");
        job.setJarByClass(LogToHbaseRunner.class);

        //设置map端的属性
        job.setMapperClass(LogToHbaseMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Put.class);

        TableMapReduceUtil.initTableReducerJob(EventLogsConstant.HBASE_TABLE_NAME,null,job
        );
        job.setNumReduceTasks(0);

        this.setInputPath(job);
        return job.waitForCompletion(true)?0:1;

    }

    /**
     * 设置路径
     * @param job
     */
    private void setInputPath(Job job) {
        String date = job.getConfiguration().get(GlobalConstants.RUNNING_DATE);
        String[] fields = date.split("_");
        Path inputPath = new Path("/flume/events/" + fields[1] + fields[2]);
        try {
            FileSystem fs = FileSystem.get(conf);
            if (fs.exists(inputPath)) {
                FileInputFormat.addInputPath(job, inputPath);
            } else {
                throw new RuntimeException("输出路径不存在");
            }
        } catch (IOException e) {
            logger.warn("获取fs对象异常!", e);
        }
    }


    /**
     * 判断hbase表是否存在，不存在则创建，存在则过
     * @param conf
     */
    private void HbaseTableExists(Configuration conf) {
        HBaseAdmin ha = null;
        try {
            ha = new HBaseAdmin(conf);
            if (!ha.tableExists(TableName.valueOf(EventLogsConstant.HBASE_TABLE_NAME))) {
                HTableDescriptor hdc = new HTableDescriptor(EventLogsConstant.HBASE_TABLE_NAME);
                HColumnDescriptor hcd = new HColumnDescriptor(EventLogsConstant.HBASE_COLUMN_FAMILY);
                // 将列簇添加到hdc
                hdc.addFamily(hcd);
                ha.createTable(hdc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ha != null) {
                try {
                    ha.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * 参数处理
     * @param args
     * @param conf
     */
    private void setArgs(String[] args, Configuration conf) {
        String date=null;
        for(int i=0;i<args.length;i++){
            if(args[i].equals("-d")){
                if(1+i <args.length){
                    date=args[i+1];
                    break;
                }

            }
        }
        //代码到这，date还是null，默认用昨天的时间
    }


}
