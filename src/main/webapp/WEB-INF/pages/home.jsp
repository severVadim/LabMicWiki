<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/nicEdit.js"/>"></script>
<script src="<c:url value="/resources/js/tree.js"/>"></script>
<script src="<c:url value="/resources/js/function.js"/>"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/treeStyle.css" />" rel="stylesheet">
<link rel='shortcut icon' type='image/ico' href=<c:url value="/resources/image/favicon.ico" /> />
<title>LabMic Wiki</title>
<body>
<div id="header"><jsp:include page="header.jsp"/></div>
<div id="menu"><jsp:include page="menu.jsp"/></div>
<div id="main">
	<div class="content-container" id="content-container">			
		<table class="inputs-table">
		<tr class="articleData">
			<td><B>Name: </B></td>
			<td><input type="text" id = "articleName" readonly></td>
			<td><B>Catalog: </B></td>
        	<td><input type="text" id = "catalogName" readonly></td>
        	<td><B>Date: </B></td>
        	<td><input type="text" id = "datetime" readonly></td>
		</tr>
		<tr class="changeArticleCatalogName">
			<td><B>New name: </B></td>
			<td><input type="text" id= "newArticleName" class="changeArticleCatalogName"></td>
			<td><B>New catalog: </B></td>
        	<td><select id= "newCatalogName" class="changeArticleCatalogName"></select></td>
		</tr>
		</table>
		<div id="preview" class="edit-preview"></div>
		<div id="content" class="edit-preview"></div>
	</div>
</div>	
</body>

<script>
var user = "${user.getNickName()}";
var articleFromServer = {id:"${content.id}", articleName:"${content.articleName}", catalogName:"${content.catalogName}", catalogId:"${content.catalogId}", content:'${content.content}', datetime:"${content.datetime}"};
var articleToServer = {};
for (var key in articleFromServer) {
	articleToServer[key] = articleFromServer[key];
	}
var contentDiv =  $("#content");
var previewDiv =  $("#preview");
var searchResultsDiv = $("#searchResults");
var contentContainerDiv = $("#content-container");
var previewBackButton = $("#preview-back");
var newButton =  $("#new");
var editButton = $("#edit");
var historyButton =  $("#history");
var inputsBoxDiv = $("#inputs-box");
var articleName = $("#articleName");
var changeArticleCatalogName = $(".changeArticleCatalogName");
var articleData = $(".articleData");
var catalogName = $("#catalogName");
var datetime = $("#datetime");
var newArticleName = $("#newArticleName");
var newCatalogName = $("#newCatalogName");

var option;  
for (var i=0; i < listArticles.length; i++){ 
	  option = $( '<option/>', {
		  'value': listArticles[i].catalogId,
		  }).appendTo(newCatalogName);
	  option.text(listArticles[i].catalogName);
}
option = $( '<option/>', {
	  'value': '0',
	  }).appendTo(newCatalogName);
option.text('Create new...');
	  
newCatalogName.prop("selectedIndex", -1);
newCatalogName.change(function(){
    var input = $( '<input/>', {
		 'id': 'newCatalogNameInput',
		 'type': 'text',
		 'placeholder': 'Input new catalog name...'
	  });
    if ($( this ).val()==='0') $( this ).replaceWith (input);       
	});	
$(document).ready(function() {	
	searchResultsDiv.hide ();
	previewBackButton.hide ();
	previewDiv.hide ();
	if (!articleFromServer.id) {
		contentContainerDiv.hide();
		historyButton.hide ();
	}else{		
		articleName.attr ({'value' : articleFromServer.articleName});
		catalogName.attr ({'value' : articleFromServer.catalogName});
		datetime.attr ({'value' : articleFromServer.datetime});
		contentDiv.html (articleFromServer.content);
		changeArticleCatalogName.hide();
		newButton.hide();
		var catalogToToggle =  $('a:contains('+ articleFromServer.catalogName + ')');
		var articleToSelect = $('a[href="'+articleFromServer.id+'"]')
        var itemCat = $(catalogToToggle[0]).parent('li');
		var itemArt = $(articleToSelect).parent('li');
        var content = $(catalogToToggle[0]).parent('li').children('ul');
        itemCat.toggleClass('closed').toggleClass('opened');
        itemArt.toggleClass('').toggleClass('selected');
		content.toggle();
	}
	
	previewBackButton.click (function () {
		var previewContent = new nicEditors.findEditor('editor').getContent();
		if ($( this ).text()==='Preview'){
			$( this ).text('Back');
			previewDiv.show();
			contentDiv.hide();
			previewDiv.html(previewContent);
			$('.changeArticleCatalogName').attr('readonly','readonly');
		}else{
			$( this ).text('Preview');
			previewDiv.hide();
			contentDiv.show();
			$('.changeArticleCatalogName').removeAttr('readonly');
		}	
	});
});
	
$('#edit, #new').click(function(e) {
	var isNew = (e.currentTarget.id === "new");
	if (!isNew && !articleFromServer.content){
		window.location.href='/GlobalEdit';	
	}
	if (user != "admin" ){
		window.location.href='/LogIn'
	}
	else contentDiv.html ("");
	if (isNew){
		articleData.hide();
		articleToServer.id = 0;
	} 
	contentContainerDiv.show();
	previewBackButton.show();
	changeArticleCatalogName.show();
	newButton.hide();
	editButton.hide();
	historyButton.hide ();
	
	  $( '<textarea/>', {
	 		 'id': 'editor',
	 		 'name': 'editor',
	 		 'style': 'width: 100%; height: 500px'
		  }).appendTo (contentDiv);

		  
		 var myNicEditor = new nicEditor({ fullPanel: true, onSave : function (content, id, instance){
		 		articleToServer.articleName = newArticleName.val();
		 		articleToServer.catalogId = newCatalogName.val();
		 		if (articleToServer.catalogId === '0') articleToServer.catalogName = $('#newCatalogNameInput').val();
		 		else articleToServer.catalogName = newCatalogName.find(":selected").text();
		 		articleToServer.content = content;
		 	 if (articleToServer.catalogName === "Create new..."){
		 		createDialog("Warning", "Incorrect catalog name");
		 		return;
		 	 }
			 if (!articleToServer.articleName || !articleToServer.catalogName) {
				 if (isNew){
			 	createDialog("Warning", "Name of catalog and article are required fields");
			 	return;
				 }else{
					 if (!articleToServer.articleName) articleToServer.articleName = articleFromServer.articleName;
					 if (!articleToServer.catalogName) articleToServer.catalogName = articleFromServer.catalogName;
					 if (!articleToServer.catalogId) articleToServer.catalogId = articleFromServer.catalogId;
				 }
			 }
			if ((articleToServer.content === articleFromServer.content) && (articleToServer.articleName === articleFromServer.articleName) && (articleToServer.catalogId === articleFromServer.catalogId)){
						  createDialog("Warning", "Article data does not change");
						  return;
			  }
			 
			 createDialog("Saving", "Are you really want to save this cool story?", function (result) {
			 	  if (result){
				 		$.ajax({
				 			type: "POST",
				 			contentType: "application/json",
				 			url: "save",
				 			data: encodeURIComponent(JSON.stringify(articleToServer)),
				 	        success: function(data) {
				 	        	window.location.href = "${pageContext.request.contextPath}"+data;
				 	        }
				 		}); 
			 			}else return;
			 		});
		 }, uploadURI : '/uploadImage'});
		  
		 myNicEditor.panelInstance('editor');
		 if (isNew)new nicEditors.findEditor('editor').setContent ("");
		 else new nicEditors.findEditor('editor').setContent(articleFromServer.content);		 
});	 

$('#history').click(function(e) {
	$(".btn-group").hide();
	contentDiv.html ("");
	var contentOld = {};
		$.ajax({
		    headers: { 
		        Accept : "text/plain; charset=utf-8",
		        "Content-Type": "text/plain; charset=utf-8"
		    },
 			type: "GET",
 			url: "history",
 			async: false,
 			data: ({"id" : articleFromServer.id}),
 	        success: function(data) {
 	        	contentOld = JSON.parse(data);
 	        }
 		}); 	
     	if (contentOld.length === 0) contentDiv.html ("History doesn't exists for this article");
     	else {
     	for (var i=0; i < contentOld.length; i++){
     		$('<a/>', {'link':contentOld[i].datetime,'href': "#"}).html (contentOld[i].datetime).appendTo(contentDiv);
     		$('<br/>').appendTo(contentDiv);
     	}
     	var his = $ ('<div/>', {'id': 'his'}).appendTo (contentDiv);
     	$('a[link]').click(function(){	 
     		var record = $(this).attr('link');
     		his.html (contentOld.filter(function(item) {if (item.datetime === record) return item.content;})[0].content);
     	});
     }
});

function search() {
 	var searchPattern = document.getElementById("searchPattern").value;
 	if (searchPattern.length == 0) return;
 	else{
	$.ajax({
	    headers: { 
	        Accept : "text/plain; charset=utf-8",
	        "Content-Type": "text/plain; charset=utf-8"
	    },
		type: "GET",
		url: "search",
		data: ({"searchPattern" : encodeURIComponent(searchPattern)}),
        success: function(response) {
        	searchResultsDiv.show();
        	searchResultsDiv.empty();
        	var searchList = JSON.parse(response);
        	if (searchList.length === 0){
        		$('<b/>', {text: 'No results found for "' + searchPattern +'".', appendTo : searchResultsDiv});
        	}else {
        	for (var i = 0; i < searchList.length; i++){
         		  $('<a/>', {
      			    'href' : searchList[i].id,
      			    text : searchList[i].articleName,
      			    appendTo : searchResultsDiv
      			  });
         		 $('<br/>').appendTo(searchResultsDiv);
        	}
        	}
        }       
});}
}

</script>
