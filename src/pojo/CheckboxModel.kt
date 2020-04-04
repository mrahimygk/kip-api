package pojo

import org.joda.time.DateTime
import java.io.Serializable

data class CheckboxModel(
   val id : String,
   val noteId : String,
   val text : String,
   val indent : Int,
   val checked : Boolean,
   val createdDate : DateTime,
   val modifiedDate : DateTime
) : Serializable