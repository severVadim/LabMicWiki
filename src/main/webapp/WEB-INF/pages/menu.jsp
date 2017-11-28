<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<script>
$(document).ready(function() {
	  $('.demo').ntm();
	});
</script>
<div class="form-box" id="form-box">
    <input type="text" id = "searchPattern" list="names" autocomplete="off" placeholder="Input search criteria">
    <input type="submit" value="" class="submit" onclick="search()">
    <datalist id="names"></datalist>    
</div>
<div id="searchResults"></div>
<div class="tree-menu demo" id="tree-menu">
</div>
<script>
   var listArticles = JSON.parse('${catalogs}');
   var catalog;
   var articleName;
   var articleId;
   var option;
   var selected = 'opened';
   var MainUl = document.createElement('ul');   
   
   for (var i=0; i < listArticles.length; i++){
		  var liMain = $('<li/>', {}).appendTo(MainUl);
		  $('<a/>', {}).html(listArticles[i].catalogName).appendTo(liMain);
		  var ulChild =  $('<ul/>', {'class': listArticles[i].catalogName +' closed'}).appendTo(liMain);
	    for (var j=0; j < listArticles[i].articles.length; j++){
	    	articleName = listArticles[i].articles[j].articleName;
	    	articleId = listArticles[i].articles[j].articleId;
			  var liChild = $('<li/>', {}).appendTo(ulChild);
			  $('<a/>', {'href': articleId}).html (articleName).appendTo(liChild);
			  
			  $('<option/>').html (articleName).appendTo ($('#names'));
	    }
   }
document.getElementById('tree-menu').appendChild(MainUl);


$("#searchPattern").bind('select', function(){
    var val = this.value;
    if($('#names option').filter(function(){
        return this.value === val;        
    }).length) {
    	var searchedArticle;
    	for (var i=0; i < listArticles.length; i++){
    		searchedArticle = listArticles[i].articles.filter(function( article ) {
      		  return article.articleName == val;
      		});
    	}
    	window.location.href='/' + searchedArticle[0].articleId;
    }
});
</script>