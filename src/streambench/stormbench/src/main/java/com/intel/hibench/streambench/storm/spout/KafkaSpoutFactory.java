/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.hibench.streambench.storm.spout;

import com.intel.hibench.streambench.storm.util.StormBenchConfig;
import org.apache.storm.kafka.*;
import org.apache.storm.kafka.trident.OpaqueTridentKafkaSpout;
import org.apache.storm.kafka.trident.TridentKafkaConfig;
import org.apache.storm.topology.base.BaseRichSpout;

public class KafkaSpoutFactory {

  public static BaseRichSpout getSpout(StormBenchConfig conf) {
    String topic = conf.topic;
    String consumerGroup = conf.consumerGroup;
    boolean readFromStart = conf.readFromStart;
    String zkHost = conf.zkHost;
    BrokerHosts brokerHosts = new ZkHosts(zkHost);
    SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, "", consumerGroup);
    spoutConfig.scheme = new KeyValueSchemeAsMultiScheme(new StringKeyValueScheme());
    spoutConfig.ignoreZkOffsets = readFromStart;
    return new KafkaSpout(spoutConfig);
  }


  public static OpaqueTridentKafkaSpout getTridentSpout(StormBenchConfig conf) {
    String topic = conf.topic;
    String consumerGroup = conf.consumerGroup;
    boolean readFromStart = conf.readFromStart;
    String zkHost = conf.zkHost;
    BrokerHosts brokerHosts = new ZkHosts(zkHost);
    TridentKafkaConfig tridentKafkaConfig = new TridentKafkaConfig(brokerHosts, topic, consumerGroup);
    tridentKafkaConfig.fetchSizeBytes = 10 * 1024;
    tridentKafkaConfig.scheme = new KeyValueSchemeAsMultiScheme(new StringKeyValueScheme());
    tridentKafkaConfig.ignoreZkOffsets = readFromStart;
    return new OpaqueTridentKafkaSpout(tridentKafkaConfig);
  }

}
