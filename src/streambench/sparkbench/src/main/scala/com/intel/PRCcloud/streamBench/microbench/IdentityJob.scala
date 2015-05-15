package com.intel.PRCcloud.streamBench.microbench

import com.intel.PRCcloud.streamBench.entity.ParamEntity
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.StreamingContext

class IdentityJob(subClassParams:ParamEntity) extends RunBenchJobWithInit(subClassParams) {

  override def processStreamData(lines:DStream[String],ssc:StreamingContext){
    lines.foreachRDD(rdd => rdd.foreach( _ => Unit ))
  }
}
