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
package io.gatling.core.action.builder

import io.gatling.core.structure.ScenarioContext

import scala.concurrent.duration.Duration

import akka.actor.{ ActorSystem, ActorRef }
import io.gatling.core.action.Pace
import io.gatling.core.session.Expression

/**
 * Builder for the Pace action
 *
 * Originally contributed by James Pickering.
 */
class PaceBuilder(interval: Expression[Duration], counter: String) extends ActionBuilder {

  def build(system: ActorSystem, next: ActorRef, ctx: ScenarioContext) =
    system.actorOf(Pace.props(interval, counter, ctx.dataWriters, next), actorName("pace"))
}
