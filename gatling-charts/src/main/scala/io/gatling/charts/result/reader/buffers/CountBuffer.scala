/**
 * Copyright 2011-2015 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.charts.result.reader.buffers

import io.gatling.core.result.CountsVsTimePlot
import io.gatling.core.result.message.{ OK, Status }

private[reader] class Counts(var oks: Int = 0, var kos: Int = 0) {

  def increment(status: Status): Unit = status match {
    case OK => oks += 1
    case _  => kos += 1
  }

  def total = oks + kos
}

private[reader] class CountsBuffer(buckets: Array[Int]) {
  val counts: Array[Counts] = Array.fill(buckets.length)(new Counts)

  def update(bucketNumber: Int, status: Status): Unit = {
    counts(bucketNumber).increment(status)
  }

  def distribution: Iterable[CountsVsTimePlot] =
    counts.view.zipWithIndex
      .map { case (count, bucketNumber) => CountsVsTimePlot(buckets(bucketNumber), count.oks, count.kos) }
}
