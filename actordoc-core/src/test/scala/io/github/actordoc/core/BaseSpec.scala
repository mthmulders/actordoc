package io.github.actordoc.core

import org.scalatest._

abstract class BaseSpec
  extends WordSpec
  with Matchers
  with Inspectors
  with WordSpecLike
  with BeforeAndAfterAll
  with BeforeAndAfterEach
{
}
