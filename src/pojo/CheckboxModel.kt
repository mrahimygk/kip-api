package pojo

import java.io.Serializable

data class CheckboxModel(
   val id : String,
   val noteId : String,
   val text : String,
   val indent : Int,
   val checked : Boolean,
   val createdDate : String,
   val modifiedDate : String
) : Serializable