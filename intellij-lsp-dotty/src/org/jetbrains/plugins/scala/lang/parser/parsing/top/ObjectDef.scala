package org.jetbrains.plugins.scala.lang.parser.parsing.top

import org.jetbrains.plugins.scala.ScalaBundle
import org.jetbrains.plugins.scala.lang.lexer.ScalaTokenTypes
import org.jetbrains.plugins.scala.lang.parser.parsing.builder.ScalaPsiBuilder

/**
* @author Alexander Podkhalyuzin
* Date: 06.02.2008
*/

/*
 *  ObjectDef ::= id ClassTemplateOpt
 */
object ObjectDef extends ObjectDef {
  override protected def classTemplateOpt = ClassTemplateOpt
}

trait ObjectDef {
  protected def classTemplateOpt: ClassTemplateOpt

  def parse(builder: ScalaPsiBuilder): Boolean = {
    builder.getTokenType match {
      case ScalaTokenTypes.tIDENTIFIER => builder.advanceLexer() //Ate identifier
      case _ =>
        builder error ScalaBundle.message("identifier.expected")
        return false
    }
    //parse extends block
    classTemplateOpt parse builder
    return true
  }
}