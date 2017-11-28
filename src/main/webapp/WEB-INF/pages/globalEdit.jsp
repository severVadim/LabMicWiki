<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/function.js"/>"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel='shortcut icon' type='image/ico' href=<c:url value="/resources/image/favicon.ico" /> />
<title>Global edit</title>
<body>
<div id="header"><jsp:include page="header.jsp"/></div>
<div id="menu">
<a href="#" class="button" action="AddNewCatalog">Add new catalog</a><br>
<a href="#" class="button" action="DeleteCatalog">Delete catalog</a><br>
<a href="#" class="button" action="RenameCatalog">Rename catalog</a><br>
<a href="#" class="button" action="DeleteArticle">Delete article</a><br>
<a href="#" class="button" action="RenameArticle">Rename article</a><br>
<a href="#" class="button" action="MoveArticle">Move article</a>
</div>
<div id=main>
	<div class="content-container" id="content-container">
	<table id = "caption"><tr><td align ="center"><b></b></td></tr></table>		
	<table class="inputs-table" id="table">		
		<tr class="AddNewCatalog">
			<td><B>Catalog name: </B></td>
			<td><input type="text" id = "catalogName" ></td>
		</tr>
		<tr class="DeleteCatalog">	
			<td><B>Select catalog for deleting: </B></td>
        	<td><select id = "catalogId" class="catalog"><option value=""></option></select></td>
		</tr>
		<tr class="RenameCatalog">
			<td><B>Select catalog: </B></td>
		    <td><select id= "catalogForRenaming" class="catalog"><option value=""></option></select></td>
		    <td><B>New catalog name: </B></td>
			<td><input type="text" id= "newCatalogName" ></td>
		</tr>
		<tr class="DeleteArticle">
			<td><B>Select catalog: </B></td>
		    <td><select id= "selectCatalogForDeletingArticle" class="catalog"><option value=""></option></select></td>
		    <td><B>Select article: </B></td>
			<td><select id= "selectArticleForDeleting" class="article"><option value=""></option></select></td>
		</tr>
		<tr class="RenameArticle">
			<td><B>Select catalog: </B></td>
		    <td><select id= "selectCatalogForRenamingArticle" class="catalog"><option value=""></option></select></td>
		    <td><B>Select article: </B></td>
			<td><select id= "selectArticleForRenaming" class="article"><option value=""></option></select></td>
			<td><B>New article name: </B></td>
			<td><input type="text" id= "newArticleName" ></td>
		</tr>
		<tr class="MoveArticle">
			<td><B>Select catalog (from): </B></td>
		    <td><select id= "selectCatalogFrom" class="catalog"><option value=""></option></select></td>
		    <td><B>Select article: </B></td>
			<td><select id= "selectArticleForMoving" class="article"><option value=""></option></select></td>
			<td><B>Select catalog (to): </B></td>
			<td><select id= "selectCatalogTo" class="catalog" ><option value=""></option></select></td>
		</tr>
		<tr class="buttonSave">
				<td><button class="button" id= "save" >Save</button></td>
		</tr>
		</table>
	</div>
</div>
<script>
var list = {};
var action;
$('a[action]').click(function(e){
	list = {};
	action = $(this).attr('action');
	var captionStr = action.split(/(?=[A-Z])/).join (" ");
	$($ ('#caption > tbody > tr > td > b')[0]).text(captionStr);
	$('#content-container').show();
	$('.'+action).show();
 	var tableRows = $('#table > tbody > tr');
 	for (var i = 0; i < tableRows.length - 1; i++){
 		if (tableRows[i].className  != action)$(tableRows[i]).hide();		
 	}
	$.ajax({
	    headers: { 
	        Accept : "text/plain; charset=utf-8",
	        "Content-Type": "text/plain; charset=utf-8"
	    },
		type: "GET",
		url: "getFreshCatalogs",
		async: false,
        success: function(data) {
        	list = JSON.parse(data);
        }
        });	
	var visibleCatalogs = $ ('.catalog:visible');
	visibleCatalogs.empty().append('<option></option>');
 	$ ('.article:visible').empty().append('<option></option>');
 	
 	for (var i=0; i < list.length; i++){ 
 		  $('<option/>', {
 			    'value' : list[i].catalogId, 
 			    text : list[i].catalogName,
 			    appendTo : visibleCatalogs
 			  });
	}
 	
 	$ ('input:visible').val("");
});

$('select.catalog').change(function(){
	var listArticles = {};
	var catalogId = $( this ).val();
	var jArticles = $ ('.article:visible');
	var index = $('select.catalog:visible').index(this);
	if ( index < 1){
	jArticles.empty().append('<option></option>');			
	if (catalogId){
		listArticles = $.grep(list, function(e){ return e.catalogId == catalogId; });
	    if (listArticles.length != 0){
	    	for (var i=0; i < listArticles[0].articles.length; i++){ 
	    		 $('<option/>', {
	    		 'value' : listArticles[0].articles[i].articleId, 
	    		  text : listArticles[0].articles[i].articleName,
	    		  appendTo : jArticles
	    		});
	    	}
	    }
	 }
	}
});	

$('#save').click(function(e){
	var toSave = [];
	var temp;
	
	toSave.push(action);
	var children = $('.' + action).children();
	for (var i = 1; i < children.length; i+=2){
		temp = $($(children[i]).children()[0]).val ();
		if (!temp) {
			createDialog("Warning", "Some fields are empty");
			return;
		}
		else toSave.push(temp);		
	}
	if (action === "DeleteCatalog"){
		listArticles = $.grep(list, function(e){ return e.catalogId == toSave[1]; });
		if (listArticles[0].articles.length > 0){
			createDialog("Warning", "Cannot remove not empty catalog");
			return;
		}
	}
	createDialog("Saving", "Do you want to save changes?", function (result) {
	 	  if (result){
		 		$.ajax({
		 			type: "POST",
		 			contentType: "application/json",
		 			url: "saveGlobal",
		 			data: encodeURIComponent(JSON.stringify(toSave)),
		 	        success: function(data) {
		 	        	window.location.href = "${pageContext.request.contextPath}"+data;
		 	        }
		 		}); 
	 			}else return;
});
});
$(document).ready(function() {	
	$('.btn-group').hide();
	$('#content-container').hide();
});
</script>
</body>
