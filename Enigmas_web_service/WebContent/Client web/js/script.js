$( document ).ready(function() {

	$(".joueur").click(function(){
		var numero = $(this).attr('id');
	});

	//Liste des joueurs
	$.ajax({
        url: "http://localhost:8080/EnigmaRest/rest/enigma/player",
        contentType: "application/xml; charset=utf-8",
        type: "GET",
        success: function(result){
        	console.log(result);
        },
        error: function(xhr, msg){
        	console.log("bite");
        }
    });

});