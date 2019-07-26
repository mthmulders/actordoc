package io.github.actordoc.core.impl

import java.nio.file.{Files, Path, Paths}

import akka.actor.Actor
import io.github.actordoc.core.ActorFinderComponent
import org.objectweb.asm.{ClassReader, ClassVisitor, Opcodes}

import scala.collection.immutable.Seq

case class ClassInfo(className: String, isActor: Boolean)

class ActorSubclassVisitor extends ClassVisitor(Opcodes.ASM5) {
  private val actorClassName = classOf[Actor].getName.replaceAll("\\.", "/")

  var className = ""
  var isActor = false

  override def visit(version: Int, access: Int, name: String, signature: String,
                     superName: String, interfaces: Array[String]): Unit = {
    className = name.replaceAll("/", ".")
    isActor = interfaces.contains(actorClassName)
  }

  def getClassInfo = ClassInfo(className, isActor)
}

trait DefaultActorFinderComponent extends ActorFinderComponent {
  def actorFinder = new DefaultActorFinder

  class DefaultActorFinder extends ActorFinder {
    private val basePath = getClass.getClassLoader
      .getResource(".")
      .toString

    private def loadClass(path: Path): Array[Byte] = Files.readAllBytes(path)

    private def processClassBytes(bytes: Array[Byte]): ClassInfo = {
      val reader = new ClassReader(bytes)
      val visitor = new ActorSubclassVisitor()
      reader.accept(visitor, 0)
      visitor.getClassInfo
    }

    private def findClasses(pkg: Package): Seq[Class[Actor]] = {
      val path = pkg.getName.replace('.', '/')
      val resource = getClass.getClassLoader.getResource(path)

      val classes = Files.walk(Paths.get(resource.toURI)).toArray
          .map(_.asInstanceOf[Path])
          .filter(_.toString.endsWith(".class"))
          .map(path => processClassBytes(loadClass(path)))
          .filter(_.isActor)
          .map(ci => Class.forName(ci.className))
          .map(_.asInstanceOf[Class[Actor]])
          .toSeq

      Seq(classes: _*)
    }

    override def findActors(p: Package): Seq[Class[Actor]] = {
      findClasses(p)
    }
  }
}
