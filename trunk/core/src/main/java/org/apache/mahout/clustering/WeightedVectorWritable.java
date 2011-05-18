/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.clustering;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

public final class WeightedVectorWritable implements Writable {

  private final VectorWritable vectorWritable = new VectorWritable();
  private double weight;

  public WeightedVectorWritable() {
  }

  public WeightedVectorWritable(double weight, Vector vector) {
    this.vectorWritable.set(vector);
    this.weight = weight;
  }

  public Vector getVector() {
    return vectorWritable.get();
  }

  public void setVector(Vector vector) {
    vectorWritable.set(vector);
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    vectorWritable.readFields(in);
    weight = in.readDouble();
  }

  @Override
  public void write(DataOutput out) throws IOException {
    vectorWritable.write(out);
    out.writeDouble(weight);
  }

  public String toString() {
    Vector vector = vectorWritable.get();
    return weight + ": " + (vector == null ? "null" : AbstractCluster.formatVector(vector, null));
  }

}