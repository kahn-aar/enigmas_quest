$( document ).ready(function() {
    //fancybox
    $(".fancybox").fancybox({
        openEffect  : 'none',
        closeEffect : 'none'
    });

    //Adresse du web service
    var url = "http://localhost:8080/EnigmaRest/rest/enigma/";

	//Liste des joueurs
	$.ajax({
        url: url+"photos",
        dataType: 'xml',
        type: "GET",
        success: function(xml){
        	$(xml).find('photo').each(function(){
                var photo = $(this).find('url').text();
                $('#corps').append("<a class=\"fancybox\" rel=\"group\" href=\""+photo+"\"><img src=\""+photo+"\" alt=\"\" class=\"image-min\"/></a>");
            });
        },
        error: function(xhr, msg){
        	console.log("error");
        }
    });

});