function createDialog(title, text, callback) {    
    return $("<div class='dialog' title='" + title + "'><p>" + text + "</p></div>").dialog({
    		   modal: true,
    		   resizable: false,
    		   height:200,
	           buttons: (title === "Saving")? {
	                "Confirm": function() {
	                    $( this ).dialog( "close" );
	                    callback(true);
	                },
	                Cancel: function() {
	                    $( this ).dialog( "close" );
	                    callback(false);
	                }
	           }:{
	                "OK": function() {
	                    $( this ).dialog( "close" );
	                }  
	           }
	           });
} 